package pt.impresa.readsecrets;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Secret;
import io.kubernetes.client.util.Config;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ReadSecretsApplication {

    private static Map<String, String> readSecrets(CoreV1Api api,String secretName) throws ApiException {
        Map<String, String> retorno = new HashMap<>();

        V1Secret segredo = api.readNamespacedSecret(secretName,"default", "true", Boolean.FALSE, Boolean.FALSE);

        Map<String,byte[]> segredos = segredo.getData();

        segredos.forEach((key, value) -> {
            String s = new String(value, StandardCharsets.UTF_8);
            retorno.put(key,s);
        });

        return retorno;
    }

    public static void main(String[] args) throws ApiException, IOException {

        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();

        readSecrets(api,"read-secrets").forEach((key, value) ->{
            System.out.println("key = " + key + " value = " + value);
        });

        readSecrets(api,"mysecret").forEach((key, value) ->{
            System.out.println("key = " + key + " value = " + value);
        });

        /// SpringApplication.run(ReadSecretsApplication.class, args);

    }

}
