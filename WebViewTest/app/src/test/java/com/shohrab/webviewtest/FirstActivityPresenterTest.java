package com.shohrab.webviewtest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by shohrab on 7/6/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class FirstActivityPresenterTest {
    @Mock
    FirstActivityContract.View mockContract;

    FirstActivityPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        mPresenter = new FirstActivityPresenter(mockContract);

    }

    @Test
    public void showErrorMessageWhenURLisEmty() throws Exception {

        when(mockContract.getUrl()).thenReturn("");
        mPresenter.clickGoButton();
        verify(mockContract).showEmptyUrlError(R.string.enter_url);
    }

    @Test
    public void showErrorMessagesWhenUrlIsInvalid() throws Exception {

        when(mockContract.getUrl()).thenReturn("check24.de");
        mPresenter.clickGoButton();
        verify(mockContract).showInvalidUrlError(R.string.invalid_url);

    }

    @Test
    public void showMainActivity() throws Exception {

        when(mockContract.getUrl()).thenReturn("https://www.check24.de/");
        mPresenter.clickGoButton();
        verify(mockContract).showMainActivity();

    }
}