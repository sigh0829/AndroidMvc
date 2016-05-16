package com.shipdream.lib.android.mvc.samples.simple.mvp;

import com.shipdream.lib.android.mvc.samples.simple.mvp.presenter.EntryPresenter;
import com.shipdream.lib.android.mvc.samples.simple.mvp.view.CounterDetailView;
import com.shipdream.lib.android.mvc.samples.simple.mvp.view.CounterBasicView;
import com.shipdream.lib.android.mvp.view.MvcActivity;
import com.shipdream.lib.android.mvp.view.MvcFragment;

import javax.inject.Inject;

public class MainActivity extends MvcActivity {
    /**
     * Define how to map navigation location id to full screen fragments
     * @param locationId The location id in string
     * @return The class of the fragment representing the navigation locations
     */
    @Override
    protected Class<? extends MvcFragment> mapNavigationFragment(String locationId) {
        switch (locationId) {
            case "LocationA":
                return CounterBasicView.class;
            case "LocationB":
                return CounterDetailView.class;
            default:
                return null;
        }
    }

    /**
     * @return The class type of the delegate fragment for the activity
     */
    @Override
    protected Class<? extends MvcActivity.DelegateFragment> getDelegateFragmentClass() {
        return ContainerFragment.class;
    }

    /**
     * Container fragment extends DelegateFragment would be the root container fragments to swap
     * full screen fragments inside it on navigation.
     */
    public static class ContainerFragment extends MvcActivity.DelegateFragment {
        @Inject
        private EntryPresenter presenter;

        /**
         * What to do when app starts for the first time
         */
        @Override
        protected void onStartUp() {
            presenter.startApp(this);
        }
    }
}
