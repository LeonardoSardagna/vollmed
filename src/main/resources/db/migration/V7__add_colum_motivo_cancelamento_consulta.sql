ALTER TABLE consultas
ADD COLUMN motivo_cancelamento ENUM('PACIENTE_DESISTIU', 'MEDICO_CANCELOU', 'OUTROS');