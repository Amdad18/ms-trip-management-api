package org.devp.trip.api;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.output.ProxyOutputStream;
import org.springframework.stereotype.Component;

import jakarta.annotation.Priority;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.WriterInterceptor;
import jakarta.ws.rs.ext.WriterInterceptorContext;

/**
 * Ignore exceptions when writing a response, which almost always means the
 * client disconnected before reading the full response.
 */

@Provider
@Priority(1)
@Component
public class ClientAbortExceptionInterceptor implements WriterInterceptor {

	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException {
		context.setOutputStream(new ClientAbortExceptionOutputStream(context.getOutputStream()));

		try {
			context.proceed();
		} catch( Exception e) {
			if(e instanceof IOException) {
				return;
			}
		} catch (Throwable throwable) {
			for (Throwable cause = throwable; cause != null; cause.getCause()) {
				if (cause instanceof ClientAbortException 
						|| cause instanceof IOException) {
					return;
				}
			}
			throw throwable;
		}
	}

	private static class ClientAbortExceptionOutputStream extends ProxyOutputStream {
		public ClientAbortExceptionOutputStream(OutputStream outputStream) {
			super(outputStream);
		}

		@Override
		protected void handleIOException(IOException e) throws IOException {
			throw new ClientAbortException(e);
		}
	}

	@SuppressWarnings("serial")
	private static class ClientAbortException extends IOException {

		public ClientAbortException(IOException e) {
			super(e);
		}
	}
}
