/**
 * Copyright © 2020 Jesse Gallagher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package us.iksg.webapp.domino.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tika.Tika;

import com.ibm.commons.util.PathUtil;
import com.ibm.commons.util.StringUtil;
import com.ibm.commons.util.io.StreamUtil;

/**
 * Mimics the Servlet 3.0 feature of serving resources from META-INF/resources.
 * 
 * <p>Note: the servlet path is taken into account when resolving resources. So, for example,
 * a servlet mapped to "/foo" will field a request for "/(app)/foo/bar.html" and look for
 * "/META-INF/resources/foo/bar.html", rather than "/META-INF/resources/bar.html".</p>
 * 
 * @author Jesse Gallagher
 */
public class ResourcesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Tika tika = new Tika();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prefix = req.getServletPath();
		String resPath = req.getPathInfo();
		
		if(prefix != null && !prefix.isEmpty()) {
			resPath = PathUtil.concat(prefix, resPath, '/');
		}

		if(StringUtil.isEmpty(resPath)) {
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		}

		if("/".equals(resPath)) { //$NON-NLS-1$
			resPath = "/index.html"; //$NON-NLS-1$
		}
		
		try(InputStream is = getClass().getResourceAsStream(PathUtil.concat("/META-INF/resources", resPath, '/'))) { //$NON-NLS-1$
			if(is != null) {
				// Guess the content type
				String contentType = tika.detect(resPath);
				if(contentType != null && contentType.startsWith("text/")) { //$NON-NLS-1$
					contentType += ";charset=UTF-8"; //$NON-NLS-1$
				}
				resp.setContentType(contentType);
				
				try(OutputStream os = resp.getOutputStream()) {
					StreamUtil.copyStream(is, os);
				}
				return;
			}
		}
		
		// Otherwise, pass to the parent
		super.doGet(req, resp);
	}
}
