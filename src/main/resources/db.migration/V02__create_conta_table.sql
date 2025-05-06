CREATE TABLE IF NOT EXISTS conta(
    id UUID PRIMARY KEY NOT NULL,
    agencia_id UUID NOT NULL,
    numero VARCHAR(100) NOT NULL,
    saldo NUMERIC(19,2),

    CONSTRAINT fk_conta_agencia  FOREIGN KEY(agencia_id) REFERENCES agencia(id)
);