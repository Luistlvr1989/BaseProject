package com.belatrixsf.baseproject.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.belatrixsf.baseproject.BaseProjectApplication
import com.belatrixsf.baseproject.R
import com.belatrixsf.baseproject.extensions.addOrShow
import com.belatrixsf.baseproject.extensions.hideIfAdded

class MainActivity : MainContract.View() {
    companion object {
        internal const val TAG_HOME = "TAG_HOME"
        internal const val TAG_ABOUT = "TAG_ABOUT"

        internal const val BUNDLE_TAG = "BUNDLE_TAG"

        fun newInstance(): Intent {
            val intent =  Intent(BaseProjectApplication.context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    private val fragments: Array<Fragment> = arrayOf()

    private var currentTag = TAG_HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareUI(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(BUNDLE_TAG, currentTag)
    }

    override fun createPresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    override fun loadCurrentFragment(currentTag: String, navItemIndex: Int) {
        val oldFragment = supportFragmentManager.findFragmentByTag(this.currentTag)
        val newFragment = fragments[navItemIndex]
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            addOrShow(R.id.flContent, newFragment, currentTag)
            hideIfAdded(oldFragment)
            commit()
        }

        this.currentTag = currentTag
        invalidateOptionsMenu()
    }

    private fun prepareUI(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            //presenter.prepareHomeFragment()
        } else {
            currentTag = savedInstanceState.getString(BUNDLE_TAG)
        }
    }
}
