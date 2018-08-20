package ru.dev.gbixahue.library.arch.mvp.view

import android.os.Bundle
import ru.dev.gbixahue.library.android.LayoutHolder
import ru.dev.gbixahue.library.android.activity.BaseActivity
import ru.dev.gbixahue.library.arch.mvp.presenter.MvpPresenter

/**
 * Created by Anton Zhilenkov on 01.07.18.
 */
abstract class BaseMvpActivity: BaseActivity(), MvpView, LayoutHolder {

	protected var presenter: MvpPresenter<in MvpView>? = null

	protected abstract fun initComponent()
	protected abstract fun getMvpPresenter(): MvpPresenter<in MvpView>?

	override fun onCreate(savedInstanceState: Bundle?) {
		initComponent()
		presenter = getMvpPresenter()
		presenter?.attached(this)
		super.onCreate(savedInstanceState)
		presenter?.created()
		setContentView(getLayoutId())
		presenter?.createView()
		presenter?.viewCreated()
	}

	override fun onStart() {
		super.onStart()
		presenter?.started()
	}

	override fun onResume() {
		super.onResume()
		presenter?.resumed()
	}

	override fun onPause() {
		super.onPause()
		presenter?.paused()
	}

	override fun onStop() {
		super.onStop()
		presenter?.stopped()
	}

	override fun onDestroy() {
		presenter?.destroyView()
		super.onDestroy()
		presenter?.viewDestroyed()
		presenter?.detached()
	}
}