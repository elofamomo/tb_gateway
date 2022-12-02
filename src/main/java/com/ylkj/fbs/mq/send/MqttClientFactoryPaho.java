/*******************************************************************************
 * Copyright (c) 2009, 2014 IBM Corp.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution. 
 *
 * The Eclipse Public License is available at 
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 *   http://www.eclipse.org/org/documents/edl-v10.php.
 *
 *******************************************************************************/

package com.ylkj.fbs.mq.send;

import java.net.URI;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;

import com.ylkj.common.bytes.UtilStr;

/**
 *
 */
public class MqttClientFactoryPaho {

	/**
	 * @param serverURI
	 * @param clientId
	 * @return MqttClient
	 * @throws Exception
	 */
	public IMqttClient createMqttClient(URI serverURI, String clientId)
			throws Exception {
		MqttClientPaho m = new MqttClientPaho(
				UtilStr.customToString(serverURI), clientId);
		return m;
	}

	/**
	 * @param serverURI
	 * @param clientId
	 * @param persistence
	 * @return MqttClient
	 * @throws Exception
	 */
	public IMqttClient createMqttClient(URI serverURI, String clientId,
			MqttClientPersistence persistence) throws Exception {
		return new MqttClientPaho(UtilStr.customToString(serverURI), clientId,
				persistence);
	}

	/**
	 * @param serverURI
	 * @param clientId
	 * @return client
	 * @throws Exception
	 */
	public IMqttAsyncClient createMqttAsyncClient(URI serverURI, String clientId)
			throws Exception {
		return new MqttAsyncClientPaho(UtilStr.customToString(serverURI),
				clientId);
	}

	/**
	 * @param serverURI
	 * @param clientId
	 * @param persistence
	 * @return client
	 * @throws Exception
	 */
	public IMqttAsyncClient createMqttAsyncClient(URI serverURI,
			String clientId, MqttClientPersistence persistence)
			throws Exception {
		return new MqttAsyncClientPaho(UtilStr.customToString(serverURI),
				clientId, persistence);
	}

	/**
   * 
   */
	public void open() {
		// empty
	}

	/**
   * 
   */
	public void close() {
		// empty
	}

	/**
   * 
   */
	public void disconnect() {
		// empty
	}

	/**
	 * @return flag indicating if this client supports High Availability
	 */
	public boolean isHighAvalabilitySupported() {
		return true;
	}

}
