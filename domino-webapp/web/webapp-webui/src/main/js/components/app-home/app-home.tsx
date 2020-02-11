/*
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
import { Component, h, State } from '@stencil/core';

@Component({
	tag: 'app-home',
	styleUrl: 'app-home.css',
	shadow: false
})
export class AppHome {
	@State() config: Object = {};
	
	componentDidLoad() {
		fetch("api/serverinfo")
			.then(res => res.json())
			.then(json => this.config = json);
	}

	render() {
		return (
			<div class='app-home'>
				<p>
					Welcome to the example webapp!
        		</p>

				<pre>{JSON.stringify(this.config, null, 4)}</pre>
			</div>
		);
	}
}
