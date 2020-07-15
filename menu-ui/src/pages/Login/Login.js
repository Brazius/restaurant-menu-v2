import React, {useContext} from "react";
import {Field, Form, Formik} from "formik";
import FormikState from "../../components/FormikState/FormikState";
import {setCredentials} from "../../api";
import {UserContext} from "../../App";
import userApi from "../../api/userApi";
import { useHistory, useLocation } from "react-router-dom"
import { Button } from '@material-ui/core'
import { TextField } from 'formik-material-ui'

const initialValues = {
    username: '',
    password: ''
}

export default () => {
    const {login} = useContext(UserContext)
    const history = useHistory();
    const location = useLocation()

    const { from } = location.state || { from: { pathname: '/' } }

    const onSubmit = values => {
        setCredentials(values)

        userApi.getUser()
            .then(({data}) => {
                login(data)
                history.replace(from)
            })
    }

    return (
        <Formik
            initialValues={initialValues}
            onSubmit={onSubmit}>
            {(props) => (
                <Form>
                    <div>
                        <label htmlFor="username">Username:</label>
                        <Field name="username" type="text" component={TextField} />
                    </div>
                    <div>
                        <label htmlFor="password">Password:</label>
                        <Field name="password" type="password" component={TextField} />
                    </div>
                    <div>
                        <Button variant="contained" color="primary" type="submit">Login</Button>
                    </div>

                </Form>
            )}
        </Formik>
    )
}
