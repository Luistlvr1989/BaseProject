package com.belatrixsf.baseproject.helpers.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.view.View
import com.belatrixsf.baseproject.R

class ProgressDialog : DialogFragment() {
    companion object {
        private val TAG = ProgressDialog::class.simpleName

        fun get(manager: FragmentManager?): ProgressDialog? {
            return manager?.findFragmentByTag(TAG) as ProgressDialog?
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = View.inflate(activity, R.layout.dialog_progress, null)
        return AlertDialog.Builder(activity!!, R.style.BaseProjectUI_ProgressDialog)
                .setView(view)
                .create()
                .apply {
                    setCanceledOnTouchOutside(false)
                }
    }

    fun show(manager: FragmentManager?) {
        get(manager)?.dismiss()
        show(manager, TAG)
    }

    @Suppress("MemberVisibilityCanBePrivate", "unused", "UNUSED_PARAMETER")
    class Builder(private val context: Context) {
        private val arguments = Bundle()

        fun create(): ProgressDialog {
            val fragment = ProgressDialog()
            fragment.isCancelable = false
            return fragment
        }

        fun show(manager: FragmentManager, tag: String): ProgressDialog {
            return create().apply {
                show(manager, tag)
            }
        }

        fun show(manager: FragmentManager): ProgressDialog {
            return create().apply {
                show(manager)
            }
        }
    }
}