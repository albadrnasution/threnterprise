<%-- 
    Document   : addstaff
    Created on : Sep 26, 2011, 3:37:16 AM
    Author     : user
--%>


        <h1>Tambah Staff</h1>
        <br />
        <form action="UserController.jsp?menu=addstaffprocess" method="POST">
            <table>
                <tr>
                    <td>Username : </td>
                    <td><input type="text" name="username" /></td>
                </tr>
                <tr>
                    <td>First Name : </td>
                    <td><input type="text" name="first_name" /></td>
                </tr>
                <tr>
                    <td>Last Name : </td>
                    <td><input type="text" name="last_name" /></td>
                </tr>
                <tr>
                    <td>Email : </td>
                    <td><input type="text" name="email" /></td>
                </tr>
                <tr>
                    <td>Previlege : </td>
                    <td>
                        <select name="previlege">
                            <option value="manager">manager</option>
                            <option value="officer">officer</option>
                            <option value="admin">admin</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Password : </td>
                    <td><input type="password" name="password" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="tambah" /></td>
                </tr>
            </table>
        </form>
