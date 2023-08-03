package com.space.moviesapp.presentation.base.fragment

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.space.moviesapp.common.extensions.observeNonNull
import com.space.moviesapp.common.types.Inflater
import com.space.moviesapp.common.utils.MoviesConstants.ERROR_FRAGMENT_TAG
import com.space.moviesapp.common.utils.MoviesConstants.LOADER_FRAGMENT_TAG
import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.dialog.ErrorDialogFragment
import com.space.moviesapp.presentation.dialog.LoaderDialogFragment
import com.space.moviesapp.presentation.model.DialogItem
import com.space.moviesapp.presentation.navigation.NavigationCommand
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass


abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(private val inflate: Inflater<VB>) :
    Fragment() {

    abstract val viewModelClass: KClass<VM>
    protected val viewModel: VM by viewModelForClass(clazz = viewModelClass)


    private var _binding: VB? = null
    val binding get() = _binding!!

    private lateinit var movieDialog: DialogFragment

    abstract fun onBind()
    open fun setObserves() {}
    open fun setListeners() {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind()
        setObserves()
        setListeners()
        observeDialog()
        observeNavigation()
        observeDialog()
    }

    private fun observeDialog() {
        viewModel.dialog.observeNonNull(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { dialog ->
                if (this::movieDialog.isInitialized)
                    movieDialog.dismiss()

                when (dialog.viewType) {
                    DialogItem.ViewType.LOADER -> {
                        dialog as DialogItem.LoaderDialog
                        if (dialog.isLoaded) {
                            movieDialog = LoaderDialogFragment()
                            movieDialog.show(childFragmentManager, LOADER_FRAGMENT_TAG)
                        }
                    }
                    DialogItem.ViewType.ERROR -> {
                        dialog as DialogItem.ErrorDialog
                        movieDialog = ErrorDialogFragment(requireContext(), dialog.onRefreshClick)
                        movieDialog.show(childFragmentManager, ERROR_FRAGMENT_TAG)
                    }
                }
            }
        }
    }

    protected fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun closeKeyBoard() {
        val imm =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun observeNavigation() {
        viewModel.navigation.observeNonNull(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }

    private fun handleNavigation(navCommand: NavigationCommand) {
        when (navCommand) {
            is NavigationCommand.ToDirection -> findNavController().navigate(navCommand.directions)
            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}