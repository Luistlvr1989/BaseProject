package com.belatrixsf.baseproject.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.belatrixsf.baseproject.helpers.dialogs.MessageDialog
import com.belatrixsf.baseproject.helpers.dialogs.ProgressDialog

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<Self: BaseActivity<Self, T>, T : BasePresenter<T, Self>> : BaseView<Self, T>, AppCompatActivity() {
    internal lateinit var presenter: T

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter.attach(this as Self, lifecycle)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressDialog = ProgressDialog.Builder(this)
                    .show(supportFragmentManager)
        } else {
            getProgressDialog()?.dismiss()
        }
    }

    override fun showError(error: String?) {
        MessageDialog.Builder(this)
                .setMessage(error)
                .setNegativeButton(android.R.string.cancel)
                .show(supportFragmentManager)
    }

    override fun showError(error: Int) {
        showError(getString(error))
    }

    private fun getProgressDialog(): ProgressDialog? = progressDialog?:ProgressDialog.get(supportFragmentManager)
}