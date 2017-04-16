package com.singhajit.sherlock.crashes;

import com.singhajit.sherlock.core.investigation.Crash;
import com.singhajit.sherlock.core.investigation.CrashViewModel;
import com.singhajit.sherlock.core.repo.CrashReports;
import com.singhajit.sherlock.crashes.action.CrashListActions;
import com.singhajit.sherlock.crashes.presenter.CrashListPresenter;

import org.hamcrest.CustomTypeSafeMatcher;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CrashListPresenterTest {
  @Test
  public void shouldRenderAllCrashes() throws Exception {
    CrashListActions actions = mock(CrashListActions.class);
    CrashListPresenter presenter = new CrashListPresenter(actions);
    CrashReports crashReports = mock(CrashReports.class);
    Crash crash1 = mock(Crash.class);
    when(crash1.getId()).thenReturn(1);
    List<Crash> crashes = asList(crash1, mock(Crash.class));
    when(crashReports.getAll()).thenReturn(crashes);

    presenter.render(crashReports);

    verify(actions).render(argThat(new CustomTypeSafeMatcher<List<CrashViewModel>>("") {
      @Override
      protected boolean matchesSafely(List<CrashViewModel> crashes) {
        return crashes.size() == 2 && crashes.get(0).getIdentifier() == 1;
      }
    }));
  }
}