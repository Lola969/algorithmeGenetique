# Documentation Belenios

## 1. Objectif

L'objectif de cette tâche est d'installer et de configurer **Belenios**, un système de vote électronique, sur un serveur.

---

## 2. Mise en place

### 2.1 Prérequis

#### Mettre à jour le système

Avant d'installer les dépendances nécessaires, commencez par mettre à jour votre système :

```bash
sudo apt update && sudo apt upgrade -y
```

#### Installer les dépendances système requises

Les paquets suivants doivent être installés :

```bash
sudo apt install bubblewrap build-essential libgmp-dev libsodium-dev pkg-config m4 \
libssl-dev libsqlite3-dev wget ca-certificates zip unzip libncurses-dev \
zlib1g-dev libgd-securityimage-perl cracklib-runtime jq npm -y
```

---

### 2.2 Cloner le dépôt Git de Belenios

Il est recommandé d'utiliser un dépôt **Git** plutôt qu'un téléchargement d'archive afin d'avoir accès aux mises à jour.

#### Installer Git

```bash
sudo apt install git
```

#### Cloner le dépôt avec HTTPS

```bash
git clone https://gitlab.com/belenios/belenios.git
cd belenios/
```

---

## 3. Compilation et exécution de Belenios

### 3.1 Installation d’OCaml, OPAM et des dépendances

Lancez le script `opam-bootstrap.sh` pour installer OCaml, OPAM et toutes les dépendances nécessaires. Assurez-vous que vous êtes dans le répertoire de Belenios :

```bash
cd belenios/
./opam-bootstrap.sh
```

⏳ **Cette commande peut durer environ 30 minutes et télécharger jusqu'à 3 Go de dépendances.**

---

### 3.2 Ajouter OCaml à `.bashrc` pour rendre la configuration permanente

Ajoutez l'environnement OCaml à votre fichier de configuration shell :

```bash
echo 'source /home/ubuntu/belenios/env.sh' >> ~/.bashrc
source ~/.bashrc
```

---

### 3.3 Supprimer les fichiers temporaires

Après l'installation, nettoyez les fichiers temporaires :

```bash
rm -rf /home/ubuntu/.belenios/bootstrap/src
```

---

### 3.4 Compiler la version de production

Compilez Belenios en version **release** :

```bash
cd /home/ubuntu/belenios
make build-release-server
```

---

### 3.5 Vérifier que tout fonctionne

Une fois la compilation terminée, exécutez le test suivant pour vous assurer que l'installation est correcte :

```bash
make check
```

Si tout fonctionne bien, vous êtes prêt à exécuter Belenios.

---

## 4. Lancer le serveur Belenios

Démarrez Belenios avec la commande suivante :

```bash
./_build/default/server/belenios_server.exe
```

Si vous souhaitez exécuter Belenios en arrière-plan :

```bash
nohup ./_build/default/server/belenios_server.exe > belenios.log 2>&1 &
```

---
