# Plan

## Current status

- Base CRUD for employees and departments is complete
- Validation, centralized exception handling, integration tests, and employee pagination/sorting are in place
- Employee filtering, status, audit timestamps, and a local H2 run profile are in place
- The next goal is to grow this from a basic CRUD project into a more realistic backend portfolio system

## Phase 1: Must-have backend upgrades

- Keep improving README with clearer setup, API examples, and architecture notes
- Keep expanding tests for unhappy paths and listing behavior

## Phase 2: Strong portfolio features

- Add Swagger / OpenAPI documentation
- Add soft delete or deactivation flow instead of only hard delete
- Add manager relationships or reporting lines between employees
- Add CSV export for employee data
- Add Flyway database migrations
- Add Docker and `docker-compose` for local setup

## Phase 3: Security and access control

- Add Spring Security
- Add JWT-based authentication
- Add role-based access control for roles such as `ADMIN`, `HR`, and `MANAGER`
- Restrict sensitive actions based on user role

## Phase 4: Optional AI extension

- Add an AI help assistant only after the core backend is strong
- Use RAG over system guides, onboarding docs, FAQ, or policy content
- Keep AI as a support feature, not as the main business logic
- Consider example use cases:
  - “How do I add a new employee?”
  - “What fields are required for onboarding?”
  - “How do I filter employees by department?”

## Priority order

1. Swagger / OpenAPI
2. Stronger tests and README improvements
3. Security with JWT and RBAC
4. Docker and Flyway
5. Optional AI assistant
