package com.example.dubizzletest.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Base class to add common functionality.
 */
abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
