package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController {
    val customers = mutableListOf<CustomerModel>()

    @GetMapping
    fun getAll(): MutableList<CustomerModel> {
        return customers
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody custumer: PostCustomerRequest){
        val id = if(customers.isEmpty()) {
            1
        } else {
            customers.last().id.toInt() + 1
        }.toString()
        customers.add(CustomerModel(id, custumer.name, custumer.email))
    }

    @GetMapping("/{id}")
    fun getCostumer(@PathVariable id: String): CustomerModel {
        return customers.first { it.id == id }
    }
}