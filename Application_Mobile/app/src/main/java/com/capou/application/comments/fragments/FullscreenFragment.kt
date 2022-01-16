package com.capou.application.comments.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.capou.application.R
import com.capou.application.databinding.AlertdialogCommentBinding
import com.capou.application.databinding.FragmentFullscreenBinding
import com.capou.application.databinding.FragmentHomeAlimentBinding

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenFragment  : DialogFragment() {

    /** The system calls this to get the DialogFragment's layout, regardless
    of whether it's being displayed as a dialog or an embedded fragment. */

    private lateinit var binding: AlertdialogCommentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout to use as dialog or embedded fragment
        binding = AlertdialogCommentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    /** The system calls this only when creating the layout in a dialog. */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.setContentView(binding.root)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        //dialog.setContentView(root);
        //dialog.getWindow()?.setBackgroundDrawable(new ColorDrawable(Color.YELLOW));
        //dialog.getWindow()?.setWindowAnimations(R.style.DialogAnimation);
        dialog.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return dialog
    }
}