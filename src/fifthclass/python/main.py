# Copyright 2016 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import webapp2
import cgi

html_body = """
<html><head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<body>
%s
</body></html>"""

class MainPage(webapp2.RequestHandler):
    def get(self):
        self.response.headers['Content-Type'] = 'text/plain'
        self.response.write('Hello, World!')

        form = cgi.FieldStorage()
        s1 = form.getvalue('word1', '')
        s2 = form.getvalue('word2', '')
        content = ''
        for index in range(0, min(len(s1), len(s2))-1):
            content += s1[index]
            content += s2[index]

        if len(s1) > len(s2):
            content += s1[len(s2):len(s1)-1]
        elif  len(s2) > len(s1):
            content += s2[len(s1):len(s2)-1]

        print "Content-type: text/html\n"
        print html_body % content

app = webapp2.WSGIApplication([
    ('/', MainPage),
], debug=True)
