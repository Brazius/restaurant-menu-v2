import React, {useContext} from "react";
import './Header.css'
import {useTranslation} from "react-i18next";
import {NavLink} from "react-router-dom";
import {setCredentials} from "../../api";
import {UserContext} from "../../App";

export default () => {

    const {i18n} = useTranslation()
    const {user, logout, loggedIn} = useContext(UserContext)

    const changeLanguage = lang => e => {
        e.preventDefault()
        i18n.changeLanguage(lang)
    }

    const logoutClick = (e) => {
        e.preventDefault()
        setCredentials(null)
        logout()
    }

    const loggedInBlock = loggedIn() ?
        (
            <>
                <span>{user.name} {user.lastName}</span>
                &nbsp;
                <a href="#" onClick={logoutClick}>Atsijungti</a>
            </>
        ) :
        <NavLink to="/login">Prisijungti</NavLink>

    return (
        <div className="header-container">

            <div className="site-name">Restaurant Meniu</div>

            <div className="header-bar">
                {loggedInBlock}
                <a href="#" onClick={changeLanguage('lt')}>LT</a>
                <a href="#" onClick={changeLanguage('en')}>EN</a>
            </div>
        </div>
    )
}
