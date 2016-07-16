package com.shohrab.webviewtest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by shohrab on 7/7/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayWithStringTest {
    @Mock
    I_PlayWithString playWithString;
    PlayWithString mPlayingWithString;

    @Before
    public void setUp() throws Exception {
        mPlayingWithString = new PlayWithString();
    }

    @Test
    public void testRepositionCharacter() throws Exception {

        //when(playWithString.repositionCharacter("abcdefg")).thenReturn("agbfced");

        assertEquals("Checking:","adcd",mPlayingWithString.repositionCharacter("abcd"));

        //verify(playWithString.repositionCharacter("abcd"));

    }
}