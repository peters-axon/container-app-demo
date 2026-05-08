# Kubernetes Deployment

This directory contains two deployment variants for the Axon Ivy Engine stack.

## ivy-simple

A minimal single-instance deployment using plain Kubernetes manifests. Useful for quick local testing without Helm.

## ivy-stack

A full Helm umbrella chart deploying Axon Ivy Engine in cluster mode alongside its required infrastructure.

### Stack components

| Component | Description |
|---|---|
| **Axon Ivy Engine** | 3-replica cluster, custom application image, exposed via Ingress |
| **MS SQL Server 2022** | Developer Edition (free for development/testing), persistent storage |
| **OpenSearch** | Single-node full-text search engine, persistent storage |

### Prerequisites

- Kubernetes cluster (e.g. Docker Desktop, Rancher Desktop)
- [Helm](https://helm.sh) installed
- Traefik ingress controller running in the cluster
- Secrets `ivy-secret` and `mssql-secret` created in the target namespace (see below)

### Required secrets

Before installing, create the following secrets in your namespace:

```bash
kubectl create secret generic ivy-secret \
  --namespace ivy12 \
  --from-literal=ADMIN_PASSWORD=<your-admin-password>

kubectl create secret generic mssql-secret \
  --namespace ivy12 \
  --from-literal=USER=sa \
  --from-literal=PASSWORD=<your-sa-password>
```

### Install

```bat
helm-install.bat
```

This creates the `ivy12` namespace and installs the Helm release into it:

```bat
kubectl create namespace ivy12
helm install ivy12 . --namespace ivy12
```

### Uninstall

```bat
helm-uninstall.bat
```

This removes all Helm-managed resources and deletes the namespace:

```bat
helm uninstall ivy12 --namespace ivy12
kubectl delete namespace ivy12
```

### Useful commands

```bash
# Render templates locally without deploying (for inspection and debugging)
helm template ivy12 ./ivy-stack --namespace ivy12

# Check the status of all pods
kubectl get pods --namespace ivy12

# Stream logs of all Ivy pods
kubectl logs --namespace ivy12 -l app.kubernetes.io/name=ivy-stack -f

# Override values at install time (e.g. different image tag)
helm install ivy12 ./ivy-stack --namespace ivy12 --set ivy.image.tag=1.2.3
```
