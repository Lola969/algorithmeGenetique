# Documentation Belenios

## 1. Objectif

L'objectif de cette tâche est d'installer **Belenios** sur la machine application.

---

## 2. Mise en place

### 2.1 Prérequis

#### Mettre à jour le système

Mise à jour du système :

```bash
sudo apt update && sudo apt upgrade -y
```

#### Installer les dépendances système requises

Les paquets suivants doivent être installés :

```bash
sudo apt install bubblewrap build-essential libgmp-dev libsodium-dev pkg-config m4 libssl-dev libsqlite3-dev wget ca-certificates zip unzip libncurses-dev zlib1g-dev libgd-securityimage-perl cracklib-runtime jq npm
```

---

### 2.2 Cloner le dépôt Git de Belenios

Utiliser un dépôt **Git** plutôt qu'un téléchargement d'archive afin d'avoir accès aux mises à jour.

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

Lancer le script `opam-bootstrap.sh` pour installer OCaml, OPAM et toutes les dépendances nécessaires sur le répertoire de Belenios :

```bash
cd belenios/
./opam-bootstrap.sh
```

Ces dépendances sont :

- base64
- hex
- dune
- atdgen
- zarith
- cryptokit
- calendar
- cmdliner
- sqlite3
- ocsipersist-sqlite-config
- eliom
- gettext-camomile
- ocamlnet
- ocamlformat
- markup



**Cette commande peut durer environ 30 minutes et télécharger jusqu'à 3 Go de dépendances.**

---

### 3.2 Ajouter OCaml à `.bashrc` pour rendre la configuration permanente

```bash
echo 'source ~/belenios/env.sh' >> ~/.bashrc
source ~/.bashrc
```

---

### 3.3 Supprimer les fichiers temporaires

```bash
rm -rf ~/.belenios/bootstrap/src
```

---

### 3.4 Compiler la version de production

Compiler Belenios en version **release** :

```bash
cd ~/belenios
make build-release-server
```

---

### 3.5 Vérifier que tout fonctionne

Une fois la compilation terminée, exécuter le test suivant pour s'assurer que l'installation est correcte :

```bash
make check
```

Si tout fonctionne bien, Belenios est prêt à être exécuté.


---
## 4. Configurer le serveur avec Ocsigenserver

Belenios utilise **Ocsigenserver** comme serveur HTTP. Il peut être configuré en utilisant le fichier `demo/ocsigenserver.conf.in`.

### 4.1 Créer un fichier de configuration valide

La configuration détaillée sera expliquée dans un autre fichier.

Copie le fichier modèle fourni :
```bash
cp demo/ocsigenserver.conf.in demo/ocsigenserver.conf
```

Édite le fichier de configuration :
```bash
nano demo/ocsigenserver.conf
```

### 4.2 Lancer Ocsigenserver

Exécuter cette commande pour démarrer le serveur avec la configuration :
```bash
ocsigenserver -c demo/ocsigenserver.conf
```

