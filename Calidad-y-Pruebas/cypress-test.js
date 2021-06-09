describe('Automated testing', function(){
  it('Browse to website', function() {
    cy.visit('http://localhost/registro.html');
  })
  
  // Si se da click al botón con los dos campos vacíos aparecerá un mensaje en un p id "mensaje" diciendo "Llenar campos"
  it('Submit empty fields', function() {
    cy.get('#loginButton').click();
    cy.get('#mensaje').should('have.text', 'Llenar campos');
  });

  // Si se da click al botón con un password de menos de 6 caracteres aparecerá un mensaje  en un p id "mensaje" diciendo "Llenar campos"
  it('Small password', function() {
    cy.get('#usuario').type('DiegoMont');
    cy.get('#password').type('12345');
    cy.get('#loginButton').click();
    cy.get('#mensaje').should('have.text', 'Llenar campos');
  });

  // Si se da click al botón con un password de mas de 6 caracteres y el usuario lleno aparecerá un mensaje  en un p id "mensaje" diciendo "Bienvenido"
  it('Login', function() {
    cy.get('#usuario').type('DiegoMont');
    cy.get('#password').type('hol-amUNDO57');
    cy.get('#loginButton').click();
    cy.get('#mensaje').should('have.text', 'Bienvenido');
  });
});