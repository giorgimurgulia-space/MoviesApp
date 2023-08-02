package com.space.moviesapp.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.space.moviesapp.common.extensions.observeNonNull
import com.space.moviesapp.common.types.Inflater
import com.space.moviesapp.common.utils.MoviesConstants.ERROR_FRAGMENT_TAG
import com.space.moviesapp.common.utils.MoviesConstants.LOADER_FRAGMENT_TAG
import com.space.moviesapp.presentation.base.vm.BaseViewModel
import com.space.moviesapp.presentation.dialog.LoaderDialogFragment
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass


abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(private val inflate: Inflater<VB>) :
    Fragment() {

    abstract val viewModelClass: KClass<VM>
    protected val viewModel: VM by viewModelForClass(clazz = viewModelClass)


    private var _binding: VB? = null
    val binding get() = _binding!!


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
    }

    private fun observeDialog() {
        viewModel.dialog.observeNonNull(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { dialog ->
                LoaderDialogFragment().show(childFragmentManager, LOADER_FRAGMENT_TAG)
                Thread.sleep(10000)
                val loader =
                    childFragmentManager.findFragmentByTag(LOADER_FRAGMENT_TAG) as LoaderDialogFragment
                loader.dismiss()
            }
        }
    }

    protected fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}