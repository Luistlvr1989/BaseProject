package com.belatrixsf.baseproject.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.belatrixsf.baseproject.helpers.dialogs.MessageDialog
import com.belatrixsf.baseproject.helpers.dialogs.ProgressDialog

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment<Self: BaseFragment<Self, T>, T : BasePresenter<T, Self>> : BaseView<Self, T>, Fragment() {
    internal lateinit var presenter: T

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter.attach(this as Self, lifecycle)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressDialog = ProgressDialog.Builder(context!!)
                    .show(childFragmentManager)
        } else {
            getProgressDialog()?.dismiss()
        }
    }

    override fun showError(error: String?) {
        MessageDialog.Builder(context!!)
                .setMessage(error)
                .show(childFragmentManager)
    }

    override fun showError(error: Int) {
        showError(getString(error))
    }

    private fun getProgressDialog(): ProgressDialog? {
        return progressDialog?: ProgressDialog.get(childFragmentManager)
    }
}