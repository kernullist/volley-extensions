package com.navercorp.volleyextensions.volleyer.builder;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.navercorp.volleyextensions.volleyer.VolleyerContext;
import com.navercorp.volleyextensions.volleyer.http.HttpContent;
import com.navercorp.volleyextensions.volleyer.http.HttpMethod;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ResponseBuilderTest {
	@Test(expected=NullPointerException.class)
	public void responseBuilderConstructorShouldThrowNpeWhenVolleyerContextIsNull() {
		// Given
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		HttpContent httpContent = new HttpContent(url, method);
		VolleyerContext nullVolleyerContext = null;
		Class<String> clazz = String.class;
		// When & Then
		new ResponseBuilder<String>(nullVolleyerContext, httpContent, clazz);
	}

	@Test(expected=NullPointerException.class)
	public void responseBuilderConstructorShouldThrowNpeWhenHttpContentIsNull() {
		// Given
		HttpContent nullHttpContent = null;
		VolleyerContext volleyerContext = new VolleyerContext();
		Class<String> clazz = String.class;
		// When & Then
		new ResponseBuilder<String>(volleyerContext, nullHttpContent, clazz);
	}
	
	@Test(expected=NullPointerException.class)
	public void responseBuilderConstructorShouldThrowNpeWhenTargetClasstIsNull() {
		// Given
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		HttpContent httpContent = new HttpContent(url, method);
		VolleyerContext volleyerContext = new VolleyerContext();
		Class<String> nullClazz = null;
		// When & Then
		new ResponseBuilder<String>(volleyerContext, httpContent, nullClazz);
	}

	@Test
	public void setListenerMethodShouldReturnSameInstanceOfBuilder() {
		// Given
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		HttpContent httpContent = new HttpContent(url, method);
		VolleyerContext volleyerContext = new VolleyerContext();
		Class<String> clazz = String.class;
		ResponseBuilder<String> builder = new ResponseBuilder<String>(volleyerContext, httpContent, clazz);
		Listener<String> listener = new Listener<String>(){
			@Override
			public void onResponse(String response) {
			}};
		// When
		ResponseBuilder<String> newBuilder = builder.setListener(listener);
		// Then
		assertTrue(builder == newBuilder);
	}

	@Test
	public void setErrorListenerMethodShouldReturnSameInstanceOfBuilder() {
		// Given
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		HttpContent httpContent = new HttpContent(url, method);
		VolleyerContext volleyerContext = new VolleyerContext();
		Class<String> clazz = String.class;
		ResponseBuilder<String> builder = new ResponseBuilder<String>(volleyerContext, httpContent, clazz);
		ErrorListener errorListener = new ErrorListener (){
			@Override
			public void onErrorResponse(VolleyError error) {
			}};
		// When
		ResponseBuilder<String> newBuilder = builder.setErrorListener(errorListener);
		// Then
		assertTrue(builder == newBuilder);
	}

	@Test(expected = IllegalStateException.class)
	public void setListenerMethodShouldThrowIllegalStateExceptionWhenExecuteMethodIsAlreadyCalled() {
		// Given
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		HttpContent httpContent = new HttpContent(url, method);
		VolleyerContext volleyerContext = new VolleyerContext();
		Class<String> clazz = String.class;
		ResponseBuilder<String> builder = new ResponseBuilder<String>(volleyerContext, httpContent, clazz);
		Listener<String> listener = new Listener<String>(){
			@Override
			public void onResponse(String response) {
			}};
		// When
		builder.execute();
		// Then
		builder.setListener(listener);
	}

	@Test(expected = IllegalStateException.class)
	public void setErrorListenerMethodShouldThrowIllegalStateExceptionWhenExecuteMethodIsAlreadyCalled() {
		// Given
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		HttpContent httpContent = new HttpContent(url, method);
		VolleyerContext volleyerContext = new VolleyerContext();
		Class<String> clazz = String.class;
		ResponseBuilder<String> builder = new ResponseBuilder<String>(volleyerContext, httpContent, clazz);
		ErrorListener errorListener = new ErrorListener (){
			@Override
			public void onErrorResponse(VolleyError error) {
			}};
		// When
		builder.execute();
		// Then
		builder.setErrorListener(errorListener);
	}

	@Test(expected = IllegalStateException.class)
	public void executeMethodShouldThrowIllegalStateExceptionWhenExecuteMethodIsAlreadyCalled() {
		// Given
		String url = "test";
		HttpMethod method = HttpMethod.GET;
		HttpContent httpContent = new HttpContent(url, method);
		VolleyerContext volleyerContext = new VolleyerContext();
		Class<String> clazz = String.class;
		ResponseBuilder<String> builder = new ResponseBuilder<String>(volleyerContext, httpContent, clazz);
		// When
		builder.execute();
		// Then
		builder.execute();
	}
}
