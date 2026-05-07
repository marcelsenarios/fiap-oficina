import { test, expect } from '@playwright/test';

test('fluxo completo oficina via API', async ({ request }) => {

  // 🔐 LOGIN
  const loginResponse = await request.post('http://localhost:8080/api/auth/login', {
    data: {
      username: 'admin',
      password: 'admin123'
    }
  });

  expect(loginResponse.ok()).toBeTruthy();

  const loginBody = await loginResponse.json();
  const token = loginBody.token || loginBody.accessToken;

  console.log('TOKEN:', token);

  const authHeaders = {
    Authorization: `Bearer ${token}`
  };

  // 🧾 CRIAR CLIENTE
  const clienteResponse = await request.post('http://localhost:8080/api/clientes', {
    headers: authHeaders,
    data: {
      nome: "Cliente Demo",
      cpfCnpj: "12345678909",
      email: "cliente@demo.com",
      telefone: "11999999999"
    }
  });

  expect(clienteResponse.ok()).toBeTruthy();

  // 🔧 CRIAR SERVIÇO
  const servicoResponse = await request.post('http://localhost:8080/api/servicos', {
    headers: authHeaders,
    data: {
      nome: "Troca de óleo",
      preco: 100
    }
  });

  expect(servicoResponse.ok()).toBeTruthy();

  // 🧱 CRIAR PEÇA
  const pecaResponse = await request.post('http://localhost:8080/api/pecas', {
    headers: authHeaders,
    data: {
      nome: "Filtro de óleo",
      preco: 50,
      estoque: 10
    }
  });

  expect(pecaResponse.ok()).toBeTruthy();

  // 🚗 CRIAR ORDEM DE SERVIÇO
  const osResponse = await request.post('http://localhost:8080/api/ordens', {
    headers: authHeaders,
    data: {
      cpfCnpjCliente: "12345678909",
      placa: "ABC1234",
      marca: "Toyota",
      modelo: "Corolla",
      ano: 2020,
      servicos: ["Troca de óleo"],
      pecas: ["Filtro de óleo"]
    }
  });

  expect(osResponse.ok()).toBeTruthy();

  const osBody = await osResponse.json();
  console.log('OS criada:', osBody);

});