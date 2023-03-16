/*
 * Copyright 2022 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.confluent.kafka.schemaregistry.rules;

/**
 * A field-level rule executor.
 */
public interface FieldRuleExecutor extends RuleExecutor {

  FieldTransform newTransform(RuleContext ctx) throws RuleException;

  default Object transform(RuleContext ctx, Object message) throws RuleException {
    try (FieldTransform transform = newTransform(ctx)) {
      if (transform != null) {
        return ctx.target().transformMessage(ctx, transform, message);
      } else {
        return message;
      }
    }
  }
}