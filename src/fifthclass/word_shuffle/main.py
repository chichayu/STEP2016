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

#!/usr/bin/env python
# -*- coding:utf-8 -*-
import webapp2
import cgi

class MainPage(webapp2.RequestHandler):
    def get(self):
        self.response.headers['Content-Type'] = 'text/html; charset=utf-8'
        self.response.write("""
          <body>
          <h1>Word Shuffle Game</h1>
          <form action="">
            <input type=text name=word1><br>
            <input type=text name=word2><br>
            <input type=submit value=Submit>
          </form>
          <hr/>
          </body>
          """)
        form = cgi.FieldStorage()
        s1 = form.getvalue('word1', '')
        s2 = form.getvalue('word2', '')
        answer = ''
        for index in range(0, min(len(s1), len(s2))):
            answer += s1[index]
            answer += s2[index]

        if len(s1) > len(s2):
            answer += s1[len(s2):len(s1)]
        elif  len(s2) > len(s1):
            answer += s2[len(s1):len(s2)]

        self.response.write(answer)

app = webapp2.WSGIApplication([
    ('/', MainPage),
], debug=True)
