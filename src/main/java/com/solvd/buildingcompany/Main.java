package com.solvd.buildingcompany;

import com.solvd.buildingcompany.domain.*;
import com.solvd.buildingcompany.service.AddressService;
import com.solvd.buildingcompany.service.BuildingCompanyService;
import com.solvd.buildingcompany.service.ClientService;
import com.solvd.buildingcompany.service.impl.AddressServiceImpl;
import com.solvd.buildingcompany.service.impl.BuildingCompanyServiceImpl;
import com.solvd.buildingcompany.service.impl.ClientServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        Address address1 = new Address();
        address1.setCity("Minsk");
        address1.setStreet("Yakuba Kolasa");
        address1.setHouseNumber("23/2");
        address1.setApartmentNumber(6);

        LocalDateTime modifiedAt = LocalDateTime.now();

        Service service1 = new Service();
        service1.setName("Building construction");
        service1.setPrice(1100);
        service1.setModifiedAt(modifiedAt);
        List<Service> services = new ArrayList<>();
        services.add(service1);

        LocalDateTime startedAt = LocalDateTime.of(2021, 8, 13, 15, 00);
        LocalDateTime endedAt = LocalDateTime.of(2021, 11, 01, 18, 45);

        Order order1 = new Order(2086);
        order1.setStartedAt(startedAt);
        order1.setEndedAt(endedAt);
        order1.setResponsiblePerson("Inochkin K.A.");
        order1.setModifiedAt(modifiedAt);
        List<Order> orders = new ArrayList<>();
        orders.add(order1);

        Material material1 = new Material();
        material1.setType("Decoration");
        material1.setName("Plaster");
        material1.setQuantity("15");
        List<Material> materials = new ArrayList<>();
        materials.add(material1);

        PaySheet paySheet1 = new PaySheet(12);
        paySheet1.setHoursWorked(124);
        paySheet1.setPaymentRublesPerHour(16);

        Client client1 = new Client("Alexander", "Dynin");
        client1.setAddress(address1);
        client1.setOrders(orders);
        List<Client> clients = new ArrayList<>();
        clients.add(client1);

        LocalDateTime dateOfBirth = LocalDateTime.of(1987, 7, 22, 4, 50);

        Employee employee1 = new Employee("Alexey", "Bondarenko");
        employee1.setAddress(address1);
        employee1.setDateOfBirth(dateOfBirth);
        employee1.setPaySheet(paySheet1);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);

        LocalDateTime createdAt = LocalDateTime.of(2021, 10, 29, 10, 32);

        Feedback feedback1 = new Feedback(client1, "I am pleased with the result");
        feedback1.setCreatedAt(createdAt);
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback1);

        LocalDateTime updatedAt = LocalDateTime.of(2021, 10, 3, 8, 55);

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setMaterials(materials);
        warehouse1.setUpdatedAt(updatedAt);
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(warehouse1);

        BuildingCompany buildingCompany1 = new BuildingCompany(UUID.randomUUID().toString());
        buildingCompany1.setAddress(address1);
        buildingCompany1.setClients(clients);
        buildingCompany1.setEmployees(employees);
        buildingCompany1.setServices(services);
        buildingCompany1.setWarehouses(warehouses);
        buildingCompany1.setFeedbacks(feedbacks);

        AddressService addressService = new AddressServiceImpl();
        address1 = addressService.create(address1);
        System.out.println(address1);

        BuildingCompanyService buildingCompanyService = new BuildingCompanyServiceImpl();
        buildingCompany1 = buildingCompanyService.create(buildingCompany1);
        System.out.println(buildingCompany1);

        Long companyId = buildingCompanyService.getIdByName("Roga i kopyta");

        ClientService clientService = new ClientServiceImpl();
        client1 = clientService.create(client1, companyId);
        System.out.println(client1);

        System.out.println(clientService.select());

        String city = address1.getCity();
        addressService.delete(city);

        clientService.select();
        buildingCompanyService.createUniqueCompanies(clientService.select());

        addressService.update(address1);
    }
}
