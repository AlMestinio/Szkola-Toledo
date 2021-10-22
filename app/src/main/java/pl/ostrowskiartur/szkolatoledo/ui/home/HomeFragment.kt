package pl.ostrowskiartur.szkolatoledo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.ostrowskiartur.szkolatoledo.R
import pl.ostrowskiartur.szkolatoledo.ui.BaseFragment
import pl.ostrowskiartur.szkolatoledo.utis.getSimpleName

class HomeFragment: BaseFragment(), HomeContract.View, View.OnClickListener {
    companion object {
        val TAG = HomeFragment().getSimpleName()
    }

    private lateinit var presenter: HomeContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_home, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        presenter = HomePresenter()
        presenter.attachView(this, requireContext())
    }

    override fun initView() {


    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

}