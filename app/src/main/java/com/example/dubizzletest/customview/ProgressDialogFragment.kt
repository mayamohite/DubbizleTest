package com.example.dubizzletest.customview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.dubizzletest.R

/**
 * Common progress indicator in a form of DialogFragment.
 *
 */
class ProgressDialogFragment : DialogFragment() {

    companion object {
        @JvmStatic
        fun newInstance(): ProgressDialogFragment {
            return ProgressDialogFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.progress_dialog, container, false)
    }

    /**
     * Show dialog when it's not already shown and fragment manager is not destroyed.
     */
    override fun show(manager: FragmentManager, tag: String?) {
        if (!isAdded && !manager.isDestroyed && !manager.isStateSaved) {
            super.show(manager, tag)
        }
    }

    /**
     * This method lets to dismiss the dialog even after activity's stated was saved.
     */
    override fun dismiss() {
        if (!isAdded) {
            return
        }
        dismissAllowingStateLoss()
    }
}
