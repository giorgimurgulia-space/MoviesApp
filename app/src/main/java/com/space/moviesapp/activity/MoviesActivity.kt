package com.space.moviesapp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayout
import com.space.moviesapp.R
import com.space.moviesapp.databinding.ActivityMoviesBinding

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                onNavDestinationSelected(
                    (Navigation.getByOrdinal(tab!!.position)),
                    navController
                )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        setDestinationChangeListener()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                com.space.movie.R.id.detailsFragment -> {
                    binding.tabLayout.visibility = View.GONE
                }
                else -> {
                    binding.tabLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun onNavDestinationSelected(navItem: Navigation, navController: NavController): Boolean {
        val builder = NavOptions.Builder().setLaunchSingleTop(true).setRestoreState(true)
        val order = navItem.ordinal
        if (
            navController.currentDestination!!.parent!!.findNode(navItem.linkedFragmentId)
                    is ActivityNavigator.Destination
        ) {
            builder.setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
        } else {
            builder.setEnterAnim(androidx.navigation.ui.R.animator.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.animator.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.animator.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.animator.nav_default_pop_exit_anim)
        }
        if (order and Menu.CATEGORY_SECONDARY == 0) {
            builder.setPopUpTo(
                navController.graph.findStartDestination().id,
                inclusive = false,
                saveState = true
            )
        }
        val options = builder.build()
        return try {
            // TODO provide proper API instead of using Exceptions as Control-Flow.
            navController.navigate(navItem.linkedFragmentId, null, options)
            // Return true only if the destination we've navigated to matches the MenuItem
            navController.currentDestination?.hierarchy?.any { it.id == navItem.linkedFragmentId }
                ?: false
        } catch (e: IllegalArgumentException) {
            val name =
                NavDestination.getDisplayName(navController.context, navItem.linkedFragmentId)
            false
        }
    }

    private fun setDestinationChangeListener() {
        navController.addOnDestinationChangedListener(
            object : NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?
                ) {
                    val count = binding.tabLayout.tabCount

                    for (i in 0 until count) {
                        if (destination.hierarchy.any { it.id == Navigation.getByOrdinal(i).linkedFragmentId }) {
                            binding.tabLayout.selectTab(binding.tabLayout.getTabAt(i))
                        }
                    }
                }
            })
    }

    enum class Navigation(val linkedFragmentId: Int) {
        HOME(com.space.movie.R.id.homeFragment), FAVOURITES(com.space.movie.R.id.favouritesFragment);

        companion object {
            fun getByOrdinal(ordinal: Int): Navigation {
                return values().firstOrNull { it.ordinal == ordinal } ?: HOME
            }
        }
    }
}