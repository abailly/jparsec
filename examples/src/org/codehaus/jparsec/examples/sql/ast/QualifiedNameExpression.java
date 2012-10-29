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
package org.codehaus.jparsec.examples.sql.ast;

import org.codehaus.jparsec.examples.common.ValueObject;

/**
 * An expression like "a.b.c".
 * 
 * @author Ben Yu
 */
public final class QualifiedNameExpression extends ValueObject implements Expression {
  public final QualifiedName qname;

  public QualifiedNameExpression(QualifiedName qname) {
    this.qname = qname;
  }
  
  public static QualifiedNameExpression of(String... names) {
    return new QualifiedNameExpression(QualifiedName.of(names));
  }
}