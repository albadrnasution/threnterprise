<%-- 
    Document   : addcustomer
    Created on : Sep 26, 2011, 3:37:16 AM
    Author     : user
--%>


        <h1>Tambah Customer</h1>
        <br />
        <form action="UserController.jsp?menu=addcustomerprocess" method="POST">
            <table>
                <tr>
                    <td>First Name : </td>
                    <td><input type="text" name="first_name" /></td>
                </tr>
                <tr>
                    <td>Last Name : </td>
                    <td><input type="text" name="last_name" /></td>
                </tr>
                <tr>
                    <td>Address : </td>
                    <td><input type="text" name="address" /></td>
                </tr>
                <tr>
                    <td>Phone : </td>
                    <td><input type="text" name="phone" /></td>
                </tr>
                <tr>
                    <td>Password : </td>
                    <td><input type="password" name="password" /></td>
                </tr>
                <tr>
                    <td>Email : </td>
                    <td><input type="text" name="email" /></td>
                </tr>
                <tr>
                    <td>Place of Birth : </td>
                    <td><input type="text" name="place_of_birth" /></td>
                </tr>
                <tr>
                    <td>Date of Birth : </td>
                    <td><input type="text" name="date_of_birth" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="tambah" /></td>
                </tr>
            </table>
        </form>
