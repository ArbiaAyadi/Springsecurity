Module de Sécurité – Spring Security (authentification, autorisation, SMTP)

Contexte
Ce module assure l’authentification, l’autorisation et la sécurité des API du système de gestion des congés. Il permet de sécuriser l’accès aux données à travers un système d’authentification basé sur JWT, une gestion des rôles utilisateurs, ainsi qu’un système de réinitialisation de mot de passe par email à l’aide de Spring Mail.

Fonctionnalités principales
Authentification et autorisation

Authentification par nom d’utilisateur et mot de passe

Génération et validation d’un token JWT

Attribution de rôles : EMPLOYEE, MANAGER, ADMIN

Sécurisation des routes côté backend (Spring Security) et frontend (Angular avec AuthGuard)

Interception des requêtes HTTP avec ajout automatique du token

Réinitialisation de mot de passe

Formulaire « mot de passe oublié » dans l’interface

Envoi d’un lien de réinitialisation sécurisé par email via Spring Mail

Endpoint sécurisé pour changer le mot de passe à l’aide du token envoyé

Stack technique
Langage : Java 17
Framework : Spring Boot
Sécurité : Spring Security + JWT
Gestion des emails : Spring Mail avec configuration SMTP
Base de données : MySQL
ORM : Spring Data JPA
Stockage des tokens : localStorage ou cookie sécurisé

Tests
Tests manuels via Postman

Vérification des tokens dans les en-têtes HTTP

Simulation de différents rôles pour valider les accès

Fichiers clés
SecurityConfig.java

JwtUtils.java

UserDetailsServiceImpl.java

MailService.java

PasswordResetController.java

Endpoints importants
POST /auth/login : connexion utilisateur

POST /auth/register : création d’un compte

POST /auth/reset-password : envoi de l’email de réinitialisation

POST /auth/change-password : changement de mot de passe

GET/PUT /users/profile : consultation et mise à jour du profil utilisateur

