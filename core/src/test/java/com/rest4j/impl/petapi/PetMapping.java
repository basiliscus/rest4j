/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rest4j.impl.petapi;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joseph Kapizza <joseph@rest4j.com>
 */
public class PetMapping {
	public Exception customMappingException;

	public List<PetRelation> petRelations(Pet pet) throws Exception {
		if (customMappingException != null)
			throw customMappingException;
		List<PetRelation> result = new ArrayList<PetRelation>();
		for (int id: pet.getFriends()) {
			result.add(new PetRelation(RelationType.friend, id));
		}
		for (int id: pet.getMated()) {
			result.add(new PetRelation(RelationType.mated, id));
		}
		for (int id: pet.getAte()) {
			result.add(new PetRelation(RelationType.ate, id));
		}
		return result;
	}

	public void petRelations(Pet pet, List<PetRelation> relations) throws Exception {
		if (customMappingException != null)
			throw customMappingException;
		pet.setFriends(new ArrayList<Integer>());
		pet.setMated(new ArrayList<Integer>());
		pet.setAte(new ArrayList<Integer>());
		for (PetRelation rel: relations) {
			switch (rel.getType()) {
				case friend:
					pet.getFriends().add(rel.getPetId());
					break;
				case mated:
					pet.getMated().add(rel.getPetId());
					break;
				case ate:
					pet.getAte().add(rel.getPetId());
					break;
			}
		}
	}
}
