#列出至少有一个员工的所有部门。
SELECT
	* 
FROM
	dept 
WHERE
	deptno IN ( SELECT deptno FROM emp GROUP BY deptno );#IN  ===  = any
SELECT
	* 
FROM
	emp 
WHERE
	empno IN ( 7369, 7499, 7566 );#列出薪金比"刘一"多的所有员工。
SELECT
	* 
FROM
	emp 
WHERE
	sal > ( SELECT sal FROM emp WHERE ename = '刘一' );# 列出所有员工的姓名及其直接上级的姓名。
SELECT
	ename,
	( SELECT ename FROM emp WHERE empno = s.mgr ) 
FROM
	emp s;#列出受雇日期早于其直接上级的所有员工。
SELECT
	e.* 
FROM
	emp e 
WHERE
	e.hiredate < ( SELECT hiredate FROM emp b WHERE b.empno = e.mgr ) #列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门。
SELECT
	dname,
	emp.* 
FROM
	dept
	LEFT JOIN emp ON dept.deptno = emp.deptno;#列出所有job为“职员”的姓名及其部门名称。
SELECT
	dname,
	emp.* 
FROM
	dept
	LEFT JOIN emp ON dept.deptno = emp.deptno 
WHERE
	job = '职员';#列出最低薪金大于1500的各种工作。
SELECT
	job 
FROM
	emp 
GROUP BY
	job 
HAVING
	min( sal ) > 1500;#列出在部门 "销售部" 工作的员工的姓名，假定不知道销售部的部门编号。
SELECT
	ename 
FROM
	emp 
WHERE
	deptno = ( SELECT deptno FROM dept WHERE dname = '销售部' );#列出薪金高于公司平均薪金的所有员工。
SELECT
	* 
FROM
	emp 
WHERE
	emp.sal > ( SELECT AVG( sal ) FROM emp );#列出与"周八"从事相同工作的所有员工。
SELECT
	* 
FROM
	emp 
WHERE
	job = ( SELECT job FROM emp WHERE ename = '周八' );#列出薪金等于部门30中员工的薪金的 所有员工的姓名和薪金。
SELECT
	ename,
	deptno,
	sal + IFNULL( comm, 0 ) 
FROM
	emp 
WHERE
	sal + IFNULL( comm, 0 ) = ANY ( SELECT sal + IFNULL( comm, 0 ) FROM emp WHERE deptno = 30 );#列出在每个部门工作的员工数量、平均工资。
SELECT
	dname,
	( SELECT count( * ) FROM emp WHERE deptno = d.deptno ),
	( SELECT avg( sal ) FROM emp WHERE deptno = d.deptno ) 
FROM
	dept d;#列出所有员工的姓名、部门名称和工资。
SELECT
	dname,
	count( ename ),
	avg( sal ) 
FROM
	dept
	LEFT JOIN emp ON dept.deptno = emp.deptno 
GROUP BY
	dept.deptno;#列出所有员工的姓名、部门名称和工资。
SELECT
	ename,
	dname,
	sal 
FROM
	dept
	RIGHT JOIN emp ON dept.deptno = emp.deptno;#列出所有部门的详细信息和部门人数。
SELECT
	dept.*,
	( SELECT count( * ) FROM emp WHERE deptno = dept.deptno ) count 
FROM
	dept;
SELECT
	d.*,
	count( ename ) 
FROM
	dept d
	LEFT JOIN emp e ON d.deptno = e.deptno 
GROUP BY
	d.deptno;
	
# 列出各种工作的最低工资。
SELECT job,min(sal) FROM emp GROUP BY job;

#列出各个部门的 经理 的最低薪金。
SELECT job,min(sal) FROM emp GROUP BY job HAVING job = '经理';

#列出所有员工的年工资,按年薪从低到高排序。 
SELECT sal*12 y_sal FROM emp ORDER BY y_sal;

#查出emp表中薪水在3000以上（包括3000）的所有员工的员工号、姓名、薪水
SELECT empno, ename, sal FROM emp WHERE sal >= 3000;

#查询出所有薪水在'陈二'之上的所有人员信息。
SELECT * FROM emp WHERE sal > (SELECT sal	FROM emp WHERE ename = '陈二');

#查询出emp表中部门编号为20，薪水在2000以上（不包括2000）的所有员工，显示他们的员工号，姓名以及薪水，以如下列名显示：员工编号 员工名字 薪水
SELECT empno '员工编号', ename '员工姓名', sal '薪水' FROM emp WHERE deptno = 20 AND sal > 2000;

#查询出emp表中所有的工作种类（无重复）
SELECT job FROM emp GROUP BY job;

#查询出所有奖金（comm）字段不为空的人员的所有信息。
SELECT * FROM emp WHERE comm IS NOT NULL;

#查询出薪水在800到2500之间（闭区间）所有员工的信息。（注：使用两种方式实现and以及between and）
SELECT * FROM emp WHERE sal >= 800 AND sal <= 2500;
SELECT * FROM emp WHERE sal BETWEEN 800 AND 2500;

#查询出员工号为7521，7900，7782的所有员工的信息。（注：使用两种方式实现，or以及in）
SELECT * FROM emp WHERE empno = 7521 or empno = 7900 or empno = 7782;
SELECT * FROM emp WHERE empno in (7521,7900,7782);

#查询出名字中有“张”字符，并且薪水在1000以上（不包括1000）的所有员工信息。
SELECT * FROM emp WHERE sal > 1000 AND ename LIKE '%张%';

#查询出名字第三个汉字是“多”的所有员工信息。
SELECT * FROM emp WHERE ename LIKE '__多%';

#将所有员工按薪水升序排序，薪水相同的按照入职时间降序排序。
SELECT * FROM emp ORDER BY sal ASC,hiredate DESC;

#将所有员工按照名字首字母升序排序，首字母相同的按照薪水降序排序。 order by convert(name using gbk) asc; 
SELECT * FROM emp ORDER BY CONVERT(substr(ename,1,1) using gbk) ASC, sal DESC; 

#查询出最早工作的那个人的名字、入职时间和薪水。
SELECT ename,hiredate,sal FROM emp WHERE hiredate = (SELECT min(hiredate) FROM emp);

#显示所有员工的名字、薪水、奖金，如果没有奖金，暂时显示100.
SELECT ename,sal,IFNULL(comm,100) comm FROM emp;

#显示出薪水最高人的职位。
SELECT job FROM emp WHERE sal = (SELECT max(sal) FROM emp);

#查出emp表中所有部门的最高薪水和最低薪水，部门编号为10的部门不显示。
SELECT d.*,min(sal) min_sal,max(sal) max_sal FROM dept d LEFT JOIN emp e ON d.deptno = e.deptno WHERE d.deptno != 10 GROUP BY d.deptno;

SELECT d.* , (SELECT min(sal) FROM emp WHERE deptno = d.deptno) min_sal,(SELECT max(sal) FROM emp WHERE deptno = d.deptno) max_sal  FROM dept d WHERE d.deptno != 10;

#删除10号部门薪水最高的员工。
SELECT *  FROM emp WHERE deptno = 10;
DELETE FROM emp WHERE deptno = 10 AND sal = (SELECT max(sal) FROM (SELECT deptno,sal FROM emp) t WHERE deptno = 10);

#将薪水最高的员工的薪水降30%。
UPDATE emp SET sal = sal * 0.7 WHERE empno = ANY (SELECT empno FROM (SELECT empno FROM emp WHERE sal >= ALL(SELECT sal FROM emp)) t);

UPDATE emp SET sal = sal*1.2 WHERE sal = (SELECT max(sal) FROM (SELECT * FROM emp) t);

#查询员工姓名，工资和 工资级别(工资>=3000 为3级，工资>2000 为2级，工资<=2000 为1级) 语法：case when ... then ... when ... then ... else ... end
SELECT 
	ename,
	sal,
	CASE 
	WHEN sal >= 3000 THEN
	    '三级'
	WHEN sal > 2000 THEN
		'二级'
	WHEN sal <= 2000 THEN
		'一级'
	END grade
FROM
	emp;
	
	