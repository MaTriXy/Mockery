/*
 * Copyright 2016 Victor Albertos
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

package io.victoralbertos.mockery.internal.integration;

import io.victoralbertos.jolyglot.GsonSpeaker;
import io.victoralbertos.mockery.api.JsonConverter;
import io.victoralbertos.mockery.api.built_in_interceptor.RxRetrofit;
import io.victoralbertos.mockery.api.built_in_mockery.DTO;
import io.victoralbertos.mockery.api.built_in_mockery.DTOJson;
import io.victoralbertos.mockery.api.built_in_mockery.Optional;
import io.victoralbertos.mockery.api.built_in_mockery.RequestBodyDTO;
import io.victoralbertos.mockery.api.built_in_mockery.Valid;
import io.victoralbertos.mockery.internal.integration.Mocks.MockModel;
import java.util.List;
import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observable;

import static io.victoralbertos.mockery.api.built_in_mockery.Valid.Template.ID;
import static io.victoralbertos.mockery.api.built_in_mockery.Valid.Template.INT;
import static io.victoralbertos.mockery.internal.integration.Mocks.MockModels;

@RxRetrofit(delay = 0, failurePercent = 0, variancePercentage = 0)
@JsonConverter(GsonSpeaker.class)
interface RestApi {
  @DTO(MockModel.class)
  Model modelWithoutObservable();

  Observable<Model> modelWithoutDTO();

  @DTO(MockModels.class)
  Observable<Model> modelWithWrongDTO();

  @DTO(MockModel.class)
  Observable<Model> model();

  @DTO(MockModel.class)
  Observable<Response<Model>> modelResponse();

  @DTO(MockModel.class)
  Observable<Model> modelWithParams(@RequestBodyDTO(MockModel.class) RequestBody modelJson,
      @Optional int optional);

  @DTO(MockModel.class)
  Observable<Response<Model>> modelWithParamsResponse(@RequestBodyDTO(MockModel.class) RequestBody modelJson,
      @Optional int optional);

  @DTO(MockModel.class)
  Observable<Model> modelWithWrongMockeryParam(@DTO(MockModels.class) Model model);

  @DTO(MockModels.class)
  Observable<List<Model>> modelsWithParam(@DTOJson(MockModels.class) String modelsJSON);

  @DTO(MockModels.class)
  Observable<Response<List<Model>>> modelsWithParamResponse(@DTOJson(MockModels.class) String modelsJSON);

  @DTO(MockModels.class)
  Observable<List<Model>> modelsWithParamWithoutMockery(Integer i1);

  @Valid(value = INT, legal = "30")
  Observable<Integer> integer();

  @DTO(MockModel.class)
  Observable<Response<Model>> id(@Valid(ID) int id);
}
