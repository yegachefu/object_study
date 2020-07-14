# Object 7장

## 객체 분해

1. 프로시저 추상화와 데이터 추상화

    프로그래밍 언어의 발전 → 좀 더 효과적인 추상화를 이용해 복잡성을 극복하기 위함

    - 프로시저 추상화

        소프트웨어가 무엇을 하는지를 추상화

        프로시저 추상화를 한다는 것은, 기능분해를 한다는 것

        기능분해 = 알고리즘 분해

    - 데이터 추상화

        소프트웨어가 무엇을 알아야 하는지를 추상화

        데이터를 중심으로 **타입을 추상화(추상 데이터 타입)** or 데이터를 중심으로 **프로시저 추상화(객체지향)**

2. 프로시저 추상화와 기능 분해 **(핵심 : 어떻게)**

알고리즘 분해 또는 기능 분해라고 함

기능 분해의 관점에서, 추상화의 단위는 프로시저이며 시스템은 프로시저를 단위로 분해가 됨

프로시저
→ 반복적 혹은 유사하게 실행되는 작업을 하나의 장소에 모아놓음으로써 로직을 재사용하고 중복을 방지할 수 있는 추상화 방법

전통적인 기능 분해 → 하향식 접근법(Top-Down Approach)를 따른다.
하향식 접근법이란, 시스템을 구성하는 가장 최상위 기능을 정의 후, 조금 더 작은 단계의 하위 기능으로 분해

하향식 접근법
→ 시스템을 구성하는 가장 최상위 기능을 정의 후, 조금 더 작은 단계의 하위 기능으로 분해한다. 세분화된 마지막 하위 기능이 프로그래밍 언어로 구현 가능한 수준까지 계속되며, 구체적이어야 한다.

[급여 관리 시스템]

기능 분해 방법으로 접근한 결과

```jsx
**직원의 급여를 계산한다.**

↓↓

**직원의 급여를 계산한다.**
	사용자로부터 소득세율을 입력받는다.
	직원의 급여를 계산한다.
	양식에 맞게 결과를 출력한다.

↓↓

직원의 급여를 계산한다.
	**사용자로부터 소득세율을 입력받는다.**
		"세율을 입력하세요" 라는 문장을 화면에 출력한다.
		키보드를 통해 세율을 입력받는다.	
	**직원의 급여를 계산한다.**
		전역 변수에 저장된 직원의 기본급 정보를 얻는다.
		급여를 계산한다.
	**양식에 맞게 결과를 출력한다.**
		"이름: {직원명}, 급여: {계산된 금액}" 형식에 따라 출력 문자열을 생성한다.

```

- 기능 분해의 결과는 최상위 기능을 수행하는 데 필요한 절차들을 실행되는 순서에 따라 나열한 것
- 설계가 필요한 이유 ⇒ 변경에 대비하기 위한 것!! (but, 하향식 접근법 및 기능 분해는 변경에 취약)

```jsx
$employees = ["직원A", "직원B", "직원C"]
$basePays = [400, 300, 250]

----------

def getTaxRate()
	print("세율을 입력하세요")
	return gets().chomp().to_f()
end

----------

def calculatePayFor(name, taxRate)
	index = $employees.index(name)
	basePay = $basePays[index]
	return basePay - (basePay * taxRate)
end

----------

def describeResult(name, pay)
	return "이름: #{name}, 급여 #{pay}"
end

----------

def main(name)
	taxRate = getTaxRate()
	pay = calculatePayFor(name, taxRate)
	puts(describeResult(name, pay))
end

```

- 기능 분해 방법 → 기능을 중심으로 필요한 데이터를 결정하는데 이는 유지보수에 다양한 문제를 야기
1. 시스템은 하나의 메인 함수로 구성되어있지 않다.
  어떤 시스템도 최초에 Release 되었던 당시의 모습을 그대로 유지하지 않고, 새로운 요구사항 및 기능 추가가 된다.
  처음에 중요하게 생각되었던 메인 함수는 동등하게 중요한 여러 함수로 전락하게 된다.
  하향식 접근법은 알고리즘이나 배치 처리에는 적합할 수 있으나, 현대적인 상호작용 시스템 및 서비스 개발에는 적합하지 않다.
2.  기능 추가나 요구사항 변경으로 인해, 메인 함수를 빈번하게 수정해야 한다.
  하향식 기능 분해의 경우에는, 새로운 기능을 추가할 때마다 매번 메인 함수를 수정해야 한다.
  이렇게 기존 코드를 수정한다는 것은 새로운 버그를 만들어낼 확률이 높아진다.

    ```jsx
    def sumOfBasePays()
    	result = 0
    	for basePay in $basePays
    		result += basePay
    	end
    	puts(result)
    end
    ```

    모든 직원들의 기본급 총합을 구하는 기능을 추가해달라는 요구사항이 생겼을 때, 들어설 자리가 마땅치 않다.

    ```jsx
    def calculatePay(name)
    	taxRate = getTaxRate()
    	pay = calculatePayFor(name, taxRate)
    	puts(describeResult(name, pay))
    end

    def main(operation, args={})
    	case(operation)
    	when :pay then calculatePay(args[:name])
    	when :basePays then sumOfBasePays()
    	end
    end
    ```

    기존 메인함수의 내용을 옮기고 조건절로 추가해야 한다. 기존 코드를 계속 수정해야 한다.

3. 비즈니스 로직이 사용자 인터페이스와 강결합이다.
하향식 접근법은, 비즈니스 로직을 설계하는 초기 단계부터 **입력 방법**과 **출력 양식**을 함께 고민하도록 강요한다.
위의 예제는 급여를 계산하는 관심사 / 사용자 인터페이스에 대한 관심사가 섞여있다.

4. 하향식 분해는, 이른 시기에 함수의 실행 순서를 고정시키기에 유연성과 재사용성이 저하된다.
하향식으로 기능을 분해하는 과정은, 무엇을 해야하는 지보다 어떻게 동작해야 하는지에 집중한다. (직원의 급여를 계산하려면 어떤 작업이 필요한가?)
하향식 접근 설계는, 처음부터 구현을 염두해두기 때문에 함수들의 실행 순서를 정의하게 된다.

5. 데이터 형식이 변경될 경우 파급효과를 예측할 수 없다.
어떤 데이터를 어떤 함수가 사용하고 있는지를 추적하기 어렵다.

3. 모듈 **(핵심 : 데이터)**

정보 은닉과 모듈

정보은닉의 핵심은, 자주 변경되는 부분을 상대적으로 덜 변경되는 안정적인 인터페이스 뒤로 감춰야 한다는 것이다.
모듈은, 변경될 가능성이 있는 비밀을 내부로 감추고 잘 정의되고 쉽게 변경되지 않을 퍼블릭 인터페이스를 외부에 제공하는 것이다.

```java
module Employees
	$employees = ["직원A", "직원B", "직원C", "직원D", "직원E", "직원F"]
	$basePays = [400, 300, 250, 1, 1, 1.5]
	$hourlys = [false, false, false, true, true, true]
	$timeCards = [0, 0, 0, 120, 120, 120]

	def Employees.calculatePay(name, taxRate)
		if (Employees.hourly?(name)) then
			pay = Employees.calculateHourlyParFor(name, taxRate)
		else
			pay = Employees.calculatePayFor(name, taxRate)
	end
end

------------------

def main(operation, args={})
	case(operation)
	when :pay then calculatePay(args[:name])
	when :basePays then sumOfBasePays()
	end
end

def calculatePay(name)
	taxRate = getTaxRate()
	pay = Employees.calculatePay(name, taxRate)
end

------------------

```

모듈의 장점과 한계

장점

- 모듈 내부의 변수가 변경되더라도, 모듈 내부에만 영향을 미친다.
내부에 정의된 변수를 직접 참조하는 코드의 위치를 모듈 내부로 제한할 수 있고, 이는 디버깅에 용이하다.
- 비즈니스 로직과 사용자 인터페이스에 대한 관심을 분리한다.
모듈 내부는 비즈니스 로직, 외부는 인터페이스로서 관리할 수 있다.
- 전역변수와 전역함수를 제거함으로서 네임스페이스 오염을 방지한다.
다른 모듈에서도 동일한 이름을 사용할 수 있다.

단점

- 인스턴스의 개념이 없다.

4. 데이터 추상화와 추상 데이터 타입

어떤 문제에 대해 추상적인 개념을 먼저 생각하는 것
ex) "직원의 급여를 계산한다"(어떻게 계산할 것인가) ⇒ "직원", "급여"를 떠올리고 "계산"에 필요한 절차를 생각

```java
Employee = Struct.new(:name, :basePay, :hourly, :timeCard) do
	def calculatePay(taxRate)
		if (hourly) then
			return calculateHourlyPay(taxRate)
		end
			return calculateSalariedPay(taxRate)
	end

private
	def calculateHourlyPay(taxRate)
		return (basePay * timeCard) - (basePay * timeCard) * taxRate
	end

	def calculateSalariedPay(taxRate)
		return basePay - (basePay * taxRate)
	end
end
```

추상 데이터 타입을 구현한 모습.
여기서의 핵심은 외부에서 인자로 전달 받던 직원의 이름은 이제 Employee타입 내부에 포함되어 있으므로,
calculatePay 오퍼레이션의 인자로 받을 필요가 없다.

```java
$employees = [
	Employee.new("직원A", 400, false, 0),
	Employee.new("직원B", 300, false, 0),
	Employee.new("직원C", 250, false, 0),
	Employee.new("직원D", 1, true, 120),
	Employee.new("직원E", 1, true, 120),
	Employee.new("직원F", 1, true, 120)
]
```

일상 생활에서, Employee라고 말할 때 상태와 행위를 가지는 독립적인 객체라는 의미를 가진다. (but, 여전히 데이터와 기능을 분리해서 바라본다)

5. 클래스

상속과 다형성을 지원한다는 것이 추상 데이터 타입과의 가장 큰 차이점이다.

예제에서,
Employee 는 직원이라는 하나의 타입처럼 보이지만, 실상은 "정규 직원", "아르바이트 직원" 처럼 두개의 타입이 공존한다.

- 추상 데이터 타입 ⇒ 오퍼레이션 기준으로 타입을 묶는다. (calculatePay, monthlyBasePay)
   객체 지향 타입 ⇒ 타입을 기준으로 오퍼레이션을 묶는다. (정규 직원, 아르바이트 직원)

- 

```java
class Employee
	attr_reader :name, :basePay

def initialize(name, basePay)
	@name = name
	@basePay = basePay
end

def calculatePay(taxRate)
	raise NotImplementedError
end

def monthlyBasePay()
	raise NotImplementedError
end
```

```java
class SalariedEmployee < Employee

def calculatePay(taxRate)
	...
end

def monthlyBasePay()
	...
end
```

```java
class HourlyEmployee < Employee

def calculatePay(taxRate)
	...
end

def monthlyBasePay()
	...
end
```

```java
$employees = [
	SalariedEmployee.new("직원A", 400),
	HourlyEmployee.new("직원A", 400)
]
```

객체를 생성하고 난 후, 디테일한 클래스 내용을 알 지 못해도 인스턴스에게 메시지를 전송하기만 하면 된다.

단순히 클래스를 구현 단위로 사용하는 것이 객체지향 프로그래밍을 한다는 것은 아니고, 타입을 기준으로 절차를 추상화 해야한다.
인스턴스 변수에 저장된 값을 기반으로 메서드 내에서 타입을 명시적으로 구분하여 구성하는 것은 객체지향을 위반한 것이라 할 수 있다.

개방-폐쇄 원칙 (Open-Closed Principle, OCP) * **~~면접볼 때 달달달달 외웠어야 했던 SOLID!!!~~**
 - 기존 코드에 아무런 영향도 미치지 않고, 새로운 객체 유형과 행위를 추가할 수 있다는 개념

추상 데이터 타입 vs 객체 지향

- 타입 추가라는 변경의 압력이 더 강한 경우 ⇒ 객체지향
- 오퍼레이션 추가라는 변경의 압력이 강한 경우 ⇒ 추상 데이터 타입


notion : https://www.notion.so/ipaddress/Object-7-74103c1cdc094c0cbb490639b1bb751c