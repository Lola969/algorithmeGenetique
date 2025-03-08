# Documentation Belenios

## 1. Objectif

L'objectif de cette tâche est d'installer **Belenios** dans un conteneur sur la machine application.

---

## 2. Installation de Docker sur la Machine Application

Avant d'exécuter un conteneur Docker pour Belenios, nous devons **installer Docker** sur la machine application en suivant les instructions officielles.

### **2.1 Mise à jour du système**

```bash
sudo apt update && sudo apt upgrade -y
```

### **2.2 Vérifier et installer les prérequis**

S'assurer que les paquets suivants sont installés :

```bash
sudo apt install -y ca-certificates curl gnupg lsb-release
```

### **2.3 Ajouter la clé GPG officielle de Docker**

```bash
sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg
```

### **2.4 Ajouter le dépôt officiel Docker**

```bash
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] \
  https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```

### **2.5 Installer Docker**

```bash
sudo apt update
sudo apt install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

### **2.6 Vérifier l'installation**

```bash
docker --version
sudo systemctl status docker
```

Si Docker **n'est pas actif**, l'activer avec :

```bash
sudo systemctl enable --now docker
```

---

## 3. Mise en place du Conteneur Docker

### **3.1 Création d'un conteneur minimaliste**

Dans un fichier `Dockerfile`, ajouter le contenu suivant :

```dockerfile
FROM ubuntu:24.04

RUN apt-get update && apt-get install -y

CMD ["/bin/bash"]
```

### **3.2 Construction et exécution du conteneur**

#### **Construire l’image Docker :**

```bash
sudo docker build -t contmin .
```

#### **Créer et lancer un conteneur basé sur cette image :**

```bash
docker run -it --name belenios -d -p 8080:8080 contmin bash
```

#### **Accéder au conteneur :**

```bash
docker exec -it belenios bash
```

---

## 4. Mise en place de Belenios

### **4.1 Prérequis**

#### Installer les dépendances système requises

```bash
apt install -y bubblewrap build-essential libgmp-dev libsodium-dev pkg-config m4 libssl-dev libsqlite3-dev wget ca-certificates zip unzip libncurses-dev zlib1g-dev libgd-securityimage-perl cracklib-runtime jq npm
```

### **4.2 Cloner le dépôt Git de Belenios**

#### **Installer Git**

```bash
apt install git
```

#### **Cloner le dépôt avec HTTPS**

```bash
cd /opt
git clone https://github.com/glondu/belenios.git
```

---

## 5. Compilation et exécution de Belenios

### **5.1 Installation d’OCaml, OPAM et des dépendances**

Lancer le script `opam-bootstrap.sh` pour installer OCaml, OPAM et toutes les dépendances nécessaires :

```bash
cd belenios/
./opam-bootstrap.sh
```

### **5.2 Ajouter OCaml à ****`.bashrc`**** pour rendre la configuration permanente**

```bash
echo 'source /opt/belenios/env.sh' >> ~/.bashrc
source ~/.bashrc
```

### **5.3 Supprimer les fichiers temporaires**

```bash
rm -rf /root/.belenios/bootstrap/src
```

### **5.4 Compiler la version de production**

```bash
cd ~/belenios
make build-release-server
```

### **5.5 Vérifier que tout fonctionne**

```bash
make check
```

Si tout fonctionne bien, Belenios est prêt à être exécuté.&#x20;
