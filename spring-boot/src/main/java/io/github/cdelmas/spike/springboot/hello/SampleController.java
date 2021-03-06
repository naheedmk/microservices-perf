/*
   Copyright 2016 Cyril Delmas

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package io.github.cdelmas.spike.springboot.hello;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import io.github.cdelmas.spike.common.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/spring/hello")
public class SampleController {

    private final AtomicLong counter = new AtomicLong(0);

    @ResponseBody
    public Message home() {
        return new Message("Hello World " + counter.incrementAndGet());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createMessage(@RequestBody Message message, UriComponentsBuilder uriBuilder) {
        return ResponseEntity.created(uriBuilder.path("/spring/hello/{id}").buildAndExpand(UUID.randomUUID().toString()).toUri()).build();
    }
}
