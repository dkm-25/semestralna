PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS USERS (
    user_id        INTEGER PRIMARY KEY AUTOINCREMENT,
    name           TEXT    NOT NULL,
    email          TEXT    NOT NULL UNIQUE,
    password_hash  TEXT    NOT NULL,
    created_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     DATETIME
);

CREATE TABLE IF NOT EXISTS GROUPS (
    group_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    name        TEXT    NOT NULL,
    description TEXT,
    created_by  INTEGER NOT NULL,
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES USERS(user_id)
);

CREATE TABLE IF NOT EXISTS MEMBERSHIPS (
    membership_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id       INTEGER NOT NULL,
    group_id      INTEGER NOT NULL,
    role          TEXT    NOT NULL,
    joined_at     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES USERS(user_id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES GROUPS(group_id) ON DELETE CASCADE,
    UNIQUE (user_id, group_id)
);

CREATE TABLE IF NOT EXISTS TASKS (
    task_id     INTEGER PRIMARY KEY AUTOINCREMENT,
    group_id    INTEGER NOT NULL,
    created_by  INTEGER NOT NULL,
    title       TEXT    NOT NULL,
    description TEXT,
    status      TEXT    NOT NULL,
    deadline    DATETIME,
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  DATETIME,
    assigned_to INTEGER,
    FOREIGN KEY (group_id)   REFERENCES GROUPS(group_id),
    FOREIGN KEY (created_by) REFERENCES USERS(user_id),
    FOREIGN KEY (assigned_to) REFERENCES USERS(user_id) ON DELETE SET NULL
);

-- ЄДИНА правильна таблиця RESOURCES
CREATE TABLE IF NOT EXISTS RESOURCES (
    resource_id INTEGER PRIMARY KEY AUTOINCREMENT,
    group_id    INTEGER NOT NULL,
    uploaded_by INTEGER NOT NULL,
    title       TEXT    NOT NULL,
    type        TEXT    NOT NULL,
    path_or_url TEXT    NOT NULL,
    uploaded_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (group_id)   REFERENCES GROUPS(group_id) ON DELETE CASCADE,
    FOREIGN KEY (uploaded_by) REFERENCES USERS(user_id)
);

CREATE TABLE IF NOT EXISTS ACTIVITY_LOG (
    log_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id   INTEGER NOT NULL,
    action    TEXT    NOT NULL,
    timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    details   TEXT,
    FOREIGN KEY (user_id) REFERENCES USERS(user_id)
);

CREATE INDEX IF NOT EXISTS idx_memberships_user ON MEMBERSHIPS(user_id);
CREATE INDEX IF NOT EXISTS idx_memberships_group ON MEMBERSHIPS(group_id);
CREATE INDEX IF NOT EXISTS idx_tasks_group ON TASKS(group_id);
CREATE INDEX IF NOT EXISTS idx_tasks_assigned_to ON TASKS(assigned_to);
CREATE INDEX IF NOT EXISTS idx_resources_group ON RESOURCES(group_id);
CREATE INDEX IF NOT EXISTS idx_activity_user ON ACTIVITY_LOG(user_id);
CREATE INDEX IF NOT EXISTS idx_activity_action ON ACTIVITY_LOG(action);
