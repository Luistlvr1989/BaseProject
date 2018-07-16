package com.belatrixsf.baseproject.helpers.dialogs

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.view.View

/**
 * Created by ltalavera on 3/6/18.
 * Handle simple messages
 */
class MessageDialog : DialogFragment() {
    companion object {
        private val TAG = MessageDialog::class.simpleName

        private const val ARG_TITLE = "title"
        private const val ARG_MESSAGE = "message"
        private const val ARG_RESPONSE_CODE = "response_code"
        private const val ARG_POSITIVE_BUTTON = "positive"
        private const val ARG_POSITIVE_BUTTON_LISTENER = "positive_listener"
        private const val ARG_NEGATIVE_BUTTON = "negative"

        private const val TAG_ACTIVITY = "activity"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments!!.getCharSequence(ARG_TITLE)
        val message = arguments!!.getCharSequence(ARG_MESSAGE)
        val responseCode = arguments!!.getInt(ARG_RESPONSE_CODE, 0)
        val positiveButtonText = arguments!!.getCharSequence(ARG_POSITIVE_BUTTON, getString(android.R.string.ok))
        val positiveButtonTag = arguments!!.getString(ARG_POSITIVE_BUTTON_LISTENER)
        val negativeButtonText = arguments!!.getCharSequence(ARG_NEGATIVE_BUTTON)

        return AlertDialog.Builder(activity!!)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, null)
                .apply {
                    negativeButtonText?.let { setNegativeButton(it, null) }
                }
                .create()
                .apply {
                    title?.let { setTitle(title) }
                    message?.let { setMessage(message) }
                    setOnShowListener {
                        positiveButtonTag?.let {
                            getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                                findListener(positiveButtonTag).onClick(it, responseCode)
                                dismiss()
                            }
                        }
                    }
                    setCanceledOnTouchOutside(false)
                }
    }

    fun show(manager: FragmentManager?) {
        show(manager, TAG)
    }

    private fun findListener(tag: String): OnClickListener {
        return if (tag == TAG_ACTIVITY) {
            activity as OnClickListener
        } else {
            parentFragment as OnClickListener
        }
    }

    interface OnClickListener {
        fun onClick(view: View, responseCode: Int)
    }

    @Suppress("MemberVisibilityCanBePrivate", "unused", "UNUSED_PARAMETER")
    class Builder(private val context: Context) {
        private val arguments = Bundle()

        fun setTitle(title: CharSequence?): Builder {
            arguments.putCharSequence(ARG_TITLE, title)
            return this
        }

        fun setTitle(@StringRes titleId: Int): Builder {
            return setTitle(context.getString(titleId))
        }

        fun setMessage(message: CharSequence?): Builder {
            arguments.putCharSequence(ARG_MESSAGE, message)
            return this
        }

        fun setMessage(@StringRes messageId: Int): Builder {
            return setMessage(context.getString(messageId))
        }

        fun setResponseCode(responseCode: Int): Builder {
            arguments.putInt(ARG_RESPONSE_CODE, responseCode)
            return this
        }

        fun <T> setPositiveButton(text: CharSequence, listener: T): Builder where T : Fragment, T : OnClickListener {
            arguments.putCharSequence(ARG_POSITIVE_BUTTON, text)
            arguments.putString(ARG_POSITIVE_BUTTON_LISTENER, listener.tag)
            return this
        }

        fun <T> setPositiveButton(@StringRes resId: Int, listener: T): Builder where T : Fragment, T : OnClickListener {
            return setPositiveButton(context.getText(resId), listener)
        }

        fun <T> setPositiveButton(text: CharSequence, listener: T): Builder where T : Activity, T : OnClickListener {
            arguments.putCharSequence(ARG_POSITIVE_BUTTON, text)
            arguments.putString(ARG_POSITIVE_BUTTON_LISTENER, TAG_ACTIVITY)
            return this
        }

        fun <T> setPositiveButton(@StringRes resId: Int, listener: T): Builder where T : Activity, T : OnClickListener {
            return setPositiveButton(context.getText(resId), listener)
        }

        fun setNegativeButton(text: CharSequence): Builder {
            arguments.putCharSequence(ARG_NEGATIVE_BUTTON, text)
            return this
        }

        fun setNegativeButton(resId: Int): Builder {
            return setNegativeButton(context.getText(resId))
        }

        fun create(): MessageDialog {
            val fragment = MessageDialog()
            fragment.arguments = arguments
            fragment.isCancelable = false
            return fragment
        }

        fun show(manager: FragmentManager, tag: String) {
            create().show(manager, tag)
        }

        fun show(manager: FragmentManager) {
            create().show(manager)
        }
    }
}