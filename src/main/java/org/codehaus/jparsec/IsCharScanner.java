/*****************************************************************************
 * Copyright (C) Codehaus.org                                                *
 * ------------------------------------------------------------------------- *
 * Licensed under the Apache License, Version 2.0 (the "License");           *
 * you may not use this file except in compliance with the License.          *
 * You may obtain a copy of the License at                                   *
 *                                                                           *
 * http://www.apache.org/licenses/LICENSE-2.0                                *
 *                                                                           *
 * Unless required by applicable law or agreed to in writing, software       *
 * distributed under the License is distributed on an "AS IS" BASIS,         *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 * See the License for the specific language governing permissions and       *
 * limitations under the License.                                            *
 *****************************************************************************/
package org.codehaus.jparsec;

import org.codehaus.jparsec.pattern.CharPredicate;

/**
 * Parses a given characgter.
 * 
 * @author Ben Yu
 */
final class IsCharScanner extends Parser<Void> {
  private final String name;
  private final CharPredicate predicate;
  
  IsCharScanner(String name, CharPredicate predicate) {
    this.name = name;
    this.predicate = predicate;
  }

  @Override boolean apply(ParseContext ctxt) {
    if (ctxt.isEof()) {
      ctxt.expected(name);
      return false;
    }
    char c = ctxt.peekChar();
    if (predicate.isChar(c)) {
      ctxt.next();
      ctxt.result = null;
      return true;
    }
    ctxt.expected(name);
    return false;
  }

  @Override
  public Incremental<Void> incrementally() {
    return new IncrementalIsCharScanner();
  }

  @Override public String toString() {
    return name;
  }

  private class IncrementalIsCharScanner extends Incremental<Void> {

    @Override
    Incremental<Void> parse(ParseContext context) {
      if (context.isEof()) {
        return this;
      }
      
      char c = context.peekChar();
      if (predicate.isChar(c)) {
        context.next();
        context.result = null;
        return new Done<Void>(null);
      } else {
        return new Failed<Void>();
      }
    }

  }

}