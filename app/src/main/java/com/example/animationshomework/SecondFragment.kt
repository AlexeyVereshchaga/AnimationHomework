package com.example.animationshomework

import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Explode
import android.transition.Scene
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    private var currentScene = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_second, container, false)

        val buttonRotate = rootView.findViewById<Button>(R.id.button_rotate)
        buttonRotate.setOnClickListener {
            rotateButton(buttonRotate)
        }

        val btnSwapViews = rootView.findViewById<Button>(R.id.button_swap_views)
        val viewContainer = rootView.findViewById<FrameLayout>(R.id.container)

        val sceneInitial =
            Scene.getSceneForLayout(viewContainer, R.layout.scene_initial, requireContext())
        val sceneSwapped =
            Scene.getSceneForLayout(viewContainer, R.layout.scene_swapped, requireContext())

        btnSwapViews.setOnClickListener {
            when (currentScene) {
                0 -> {
                    TransitionManager.go(sceneSwapped, object : TransitionSet() {
                        init {
                            duration = 300
                            ordering = ORDERING_SEQUENTIAL
                            addTransition(ChangeBounds())
                        }
                    })
                    currentScene = 1
                }

                1 -> {
                    TransitionManager.go(sceneInitial, object : TransitionSet() {
                        init {
                            duration = 300
                            ordering = ORDERING_SEQUENTIAL
                            addTransition(Explode())
                        }
                    })
                    currentScene = 0
                }
            }
        }

        return rootView
    }

    private fun rotateButton(button: Button) {
        ObjectAnimator.ofFloat(button, "rotation", 0f, 360f).apply {
            duration = 1000
            interpolator = BounceInterpolator()
            start()
        }
    }
}