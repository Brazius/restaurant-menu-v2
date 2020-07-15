import React from "react";
import { Redirect, Route, Switch } from "react-router-dom";
import ProductList from "../../pages/ProductList/ProductList";
import ProductForm from "../../pages/ProductForm/ProductForm";
import ProductPage from "../../pages/ProductPage/ProductPage";
import Login from "../../pages/Login/Login";
import Secured from "../Secured/Secured";
import PrivateRoute from "../PrivateRoute/PrivateRoute";

export default () => (
    <Switch>
        <Redirect exact from="/" to="/products" />

        <Route path="/login">
            <Login/>
        </Route>

        <PrivateRoute exact path="/products/product" role="ADMIN">
            <ProductForm />
        </PrivateRoute>

        <Route path="/products/:id">
            <ProductPage/>
        </Route>

        <Route path="/products">
            <ProductList />
        </Route>

        <Route>
            <h1>Puslapis nerastas!</h1>
        </Route>
    </Switch>
)
