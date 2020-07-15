import React from "react";
import './Menu.css'
import {NavLink} from "react-router-dom";
import {useTranslation} from "react-i18next";
import Secured from "../Secured/Secured";

export default () => {

    const {t} = useTranslation("menu")

    return (
        <div className="menu-container">
            <NavLink to="/products" activeClassName="selected">{t("products")}</NavLink>
        </div>
    )
}
