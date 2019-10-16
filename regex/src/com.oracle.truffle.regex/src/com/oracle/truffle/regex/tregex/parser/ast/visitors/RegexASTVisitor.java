/*
 * Copyright (c) 2018, 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or
 * data (collectively the "Software"), free of charge and under any and all
 * copyright rights in the Software, and any and all patent rights owned or
 * freely licensable by each licensor hereunder covering either (i) the
 * unmodified Software as contributed to or provided by such licensor, or (ii)
 * the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 *
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 *
 * The above copyright notice and either this complete permission notice or at a
 * minimum a reference to the UPL must be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.oracle.truffle.regex.tregex.parser.ast.visitors;

import com.oracle.truffle.regex.tregex.parser.ast.BackReference;
import com.oracle.truffle.regex.tregex.parser.ast.CharacterClass;
import com.oracle.truffle.regex.tregex.parser.ast.Group;
import com.oracle.truffle.regex.tregex.parser.ast.LookAheadAssertion;
import com.oracle.truffle.regex.tregex.parser.ast.LookBehindAssertion;
import com.oracle.truffle.regex.tregex.parser.ast.MatchFound;
import com.oracle.truffle.regex.tregex.parser.ast.PositionAssertion;
import com.oracle.truffle.regex.tregex.parser.ast.RegexASTNode;
import com.oracle.truffle.regex.tregex.parser.ast.Sequence;

public abstract class RegexASTVisitor {

    protected abstract void visit(BackReference backReference);

    protected abstract void visit(Group group);

    protected abstract void leave(Group group);

    protected abstract void visit(Sequence sequence);

    protected abstract void leave(Sequence sequence);

    protected abstract void visit(PositionAssertion assertion);

    protected abstract void visit(LookBehindAssertion assertion);

    protected abstract void leave(LookBehindAssertion assertion);

    protected abstract void visit(LookAheadAssertion assertion);

    protected abstract void leave(LookAheadAssertion assertion);

    protected abstract void visit(CharacterClass characterClass);

    protected abstract void visit(MatchFound matchFound);

    protected void doVisit(RegexASTNode cur) {
        if (cur == null) {
            throw new IllegalStateException();
        }
        if (cur instanceof Group) {
            visit((Group) cur);
        } else if (cur instanceof Sequence) {
            visit((Sequence) cur);
        } else if (cur instanceof PositionAssertion) {
            visit((PositionAssertion) cur);
        } else if (cur instanceof LookBehindAssertion) {
            visit((LookBehindAssertion) cur);
        } else if (cur instanceof LookAheadAssertion) {
            visit((LookAheadAssertion) cur);
        } else if (cur instanceof CharacterClass) {
            visit((CharacterClass) cur);
        } else if (cur instanceof BackReference) {
            visit((BackReference) cur);
        } else if (cur instanceof MatchFound) {
            visit((MatchFound) cur);
        } else {
            throw new IllegalStateException();
        }
    }

    protected void doLeave(RegexASTNode cur) {
        if (cur instanceof Group) {
            leave((Group) cur);
        } else if (cur instanceof Sequence) {
            leave((Sequence) cur);
        } else if (cur instanceof LookBehindAssertion) {
            leave((LookBehindAssertion) cur);
        } else if (cur instanceof LookAheadAssertion) {
            leave((LookAheadAssertion) cur);
        } else {
            throw new IllegalStateException();
        }
    }
}
