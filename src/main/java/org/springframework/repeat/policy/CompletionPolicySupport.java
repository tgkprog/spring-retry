/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.repeat.policy;

import org.springframework.repeat.CompletionPolicy;
import org.springframework.repeat.RepeatContext;
import org.springframework.repeat.RepeatStatus;
import org.springframework.repeat.context.RepeatContextSupport;

/**
 * Very simple base class for {@link CompletionPolicy} implementations.
 * 
 * @author Dave Syer
 * 
 */
public class CompletionPolicySupport implements CompletionPolicy {

	/**
	 * If exit status is not continuable return <code>true</code>, otherwise
	 * delegate to {@link #isComplete(RepeatContext)}.
	 * 
	 * @see org.springframework.repeat.CompletionPolicy#isComplete(org.springframework.repeat.RepeatContext,
	 * RepeatStatus)
	 */
	public boolean isComplete(RepeatContext context, RepeatStatus result) {
		if (result != null && !result.isContinuable()) {
			return true;
		}
		else {
			return isComplete(context);
		}
	}

	/**
	 * Always true.
	 * 
	 * @see org.springframework.repeat.CompletionPolicy#isComplete(org.springframework.repeat.RepeatContext)
	 */
	public boolean isComplete(RepeatContext context) {
		return true;
	}

	/**
	 * Build a new {@link RepeatContextSupport} and return it.
	 * 
	 * @see org.springframework.repeat.CompletionPolicy#start(RepeatContext)
	 */
	public RepeatContext start(RepeatContext context) {
		return new RepeatContextSupport(context);
	}

	/**
	 * Increment the context so the counter is up to date. Do nothing else.
	 * 
	 * @see org.springframework.repeat.CompletionPolicy#update(org.springframework.repeat.RepeatContext)
	 */
	public void update(RepeatContext context) {
		if (context instanceof RepeatContextSupport) {
			((RepeatContextSupport) context).increment();
		}
	}

}