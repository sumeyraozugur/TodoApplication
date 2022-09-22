package com.example.todo.ui.splash

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentSplashBinding
import com.example.todo.delegate.viewBinding
import com.example.todo.util.sent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val binding by viewBinding(FragmentSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* val downFromUp = AnimationUtils.loadAnimation(context,R.anim.down_from_up)
        binding.splashText.animation = downFromUp*/

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            findNavController().navigate(R.id.action_splashFragment_to_viewPageFragment)

        }
    }


}