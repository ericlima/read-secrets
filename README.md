# Getting Started

environment variable to inform kube config

KUBECONFIG=/home/egvlima/.kube/config


create a secret

* first format: using CLI

kubectl create secret generic spring-security \
--from-literal=spring.user.name=demo \
--from-literal=spring.user.password=password


kubectl create secret generic read-secrets --from-literal=secret-key="Java@1Z0803"

* second format: using a yaml file

to encode a secret to base64:

echo -n 'th1s1sas3cr3t' | base64
dGgxczFzYXMzY3IzdA==

echo -n 'Java@1Z0803' | base64
SmF2YUAxWjA4MDM=

to apply a secret:

kubectl apply -f create-secret.yaml

references:
secrets:
https://kubernetes.io/docs/tasks/configmap-secret/managing-secret-using-config-file/

configmaps:
https://kubernetes.io/docs/concepts/configuration/configmap/

https://phoenixnap.com/kb/kubernetes-secrets#:~:text=1%20Create%20Kubernetes%20Secrets%202%20To%20start%20creating,keys%20for%20values%20stored%20in%20the%20secret,%20
